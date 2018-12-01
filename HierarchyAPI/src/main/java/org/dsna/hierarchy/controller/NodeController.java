package org.dsna.hierarchy.controller;

import java.util.ArrayList;
import java.util.List;

import org.dsna.hierarchy.constants.APIConstants;
import org.dsna.hierarchy.model.Node;
import org.dsna.hierarchy.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*
 * Controller to expose operations on Nodes
 */
@RestController
public class NodeController {

	@Autowired
	private NodeService nodeService;
	
	/*
	 * This Method returns all the nodes in the collection.
	 */
	@RequestMapping(APIConstants.MAPPING_NODES)
	public List<Node> getAllNodes() {
		List<Node> nodes = new ArrayList<Node>();
		nodes =  nodeService.getAllNodes();
		return nodes;
	}
	
	/*
	 * This Method insert a node in to the collection.
	 */
	@RequestMapping(value = APIConstants.MAPPING_NODE, method=RequestMethod.POST)
	public void insertNode(@RequestBody Node node) {
		nodeService.insertNode(node);
	}
	
	/*
	 * This Method returns all the child nodes of given node.
	 */
	@RequestMapping(APIConstants.MAPPING_CHILDNODES_WITH_ID)
	public List<Node> getAllChildNodes(@PathVariable String nodeId) {
		List<Node> nodes = new ArrayList<Node>();
		nodes =  nodeService.getAllChildNodes(nodeId);
		return nodes;
	}
	
	/*
	 * This Method change the parent node of a given node.
	 * Parameters : nodeId(id of given node), newParentId(id of parent node)
	 */
	@RequestMapping(value=APIConstants.MAPPING_CHANGEPARENT)
	@ResponseBody
	public void changeParentNode(@RequestParam(APIConstants.NODE) String nodeId , @RequestParam(APIConstants.PARENT) String newParentId) {
		nodeService.changeParentNode(nodeId, newParentId);
	}
	
}
