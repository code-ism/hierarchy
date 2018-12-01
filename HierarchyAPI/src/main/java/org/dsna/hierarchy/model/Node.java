package org.dsna.hierarchy.model;

import org.dsna.hierarchy.AppConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * This class models the Node entities.
 * Each node is a representation of Node in the Tree Hierarchy
 */
@Document(collection=AppConstants.COLLECTION_NODE)
public class Node {

	@Id private String nodeId;
	private String parentNode;
	private String rootNode;
	private int height;
	
	public Node(String nodeId, String parentNode, String rootNode, int height) {
		super();
		this.nodeId = nodeId;
		this.parentNode = parentNode;
		this.rootNode = rootNode;
		this.height = height;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getParentNode() {
		return parentNode;
	}

	public void setParentNode(String parentNode) {
		this.parentNode = parentNode;
	}

	public String getRootNode() {
		return rootNode;
	}

	public void setRootNode(String rootNode) {
		this.rootNode = rootNode;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
