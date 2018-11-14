package org.dsna.hierarchy.node;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NodeRepository extends MongoRepository<Node, String>{

	public List<Node> findByParentNode(String parentNodeId);
	public Node findByNodeId(String nodeId);
}
