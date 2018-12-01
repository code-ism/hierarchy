package org.dsna.hierarchy.service;

import java.util.ArrayList;
import java.util.List;

import org.dsna.hierarchy.constants.AppConstants;
import org.dsna.hierarchy.exception.NodeNotFoundException;
import org.dsna.hierarchy.model.Node;
import org.dsna.hierarchy.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * Service class for Node entity.
 */
@Service
public class NodeService {

	@Autowired
	private NodeRepository nodeRepository;
	
	
	/*
	 * This Service method returns all the nodes from collection.
	 */
	public List<Node> getAllNodes() {
		List<Node> nodes = new ArrayList<Node>();
		nodes = nodeRepository.findAll();
		return nodes;
	}
	
	/*
	 * This Service method returns all the child nodes of given node.
	 */
	public List<Node> getAllChildNodes(String nodeId) {
		List<Node> nodes = new ArrayList<Node>();
		nodes = nodeRepository.findByParentNode(nodeId);
		return nodes;
	}
	
	/*
	 * This Method change the parent node of a given node.
	 * Also updates the height of nodes once the parent node has changed.
	 * Parameters : nodeId(id of given node), newParentId(id of parent node)
	 */
	@ExceptionHandler(NodeNotFoundException.class)
	public void changeParentNode(String nodeId, String newParentId) {
		
		Node givenNode = nodeRepository.findByNodeId(nodeId);
		Node newParentNode = nodeRepository.findByNodeId(newParentId);
		if(givenNode==null || newParentNode==null){
			throw new NodeNotFoundException(AppConstants.ERROR_NODE_NOT_FOUND);
		}
		int heightOfgivenNode = givenNode.getHeight();
		int heightOfnewParentNode = newParentNode.getHeight();
		givenNode.setParentNode(newParentId);
		givenNode.setHeight(heightOfnewParentNode +1);
		nodeRepository.save(givenNode);
		
		List<Node> childNodes = new ArrayList<Node>();
		childNodes = nodeRepository.findByParentNode(nodeId);
		for (Node node : childNodes) {
			int deltaHeight = node.getHeight() - heightOfgivenNode;
			int newHeightOfNode = heightOfnewParentNode + deltaHeight + 1;
			node.setHeight(newHeightOfNode);
			nodeRepository.save(node);
		}
	}
	
	
	/*
	 * This Service method a given node to the collection.
	 */
	public void insertNode(Node node) {
		nodeRepository.save(node);
	}
}
