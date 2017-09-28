package com.mattr.pollLitHub.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class StoryNode {
	@Id
	@GeneratedValue
	private Long id;
	//Columns
	@Size(min=1)
	private String blurb;
	@Size(min=1)
	private String content;
	
	//Relationships
	//Type
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="nodetype_id")
	private NodeType nType;
	//World
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="world_id")
	private World world;
	//Clearance
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="clearance_id")
	private Clearance clearance;
	
//	//Head Node
//	@OneToOne(mappedBy="head", fetch=FetchType.LAZY)
//	private World headofWorld;
	//SelfJoin
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="super_id")
	private StoryNode superNode;//superNode must by a type whose id is < this node's id
	@OneToMany(mappedBy="superNode", fetch=FetchType.LAZY)
	private List<StoryNode> subNodes;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "nodes_characters", 
		joinColumns = @JoinColumn(name = "snode_id"), 
		inverseJoinColumns = @JoinColumn(name = "character_id"))
	private List<Chara> charas;
	
	//node location: user to keep track of the timeline
	private int nodeNum;
//	//How to track where node is in the order Node's previous and next
//	@OneToOne(mappedBy="next", fetch=FetchType.LAZY)
//	private StoryNode previous;
//	@OneToOne(mappedBy="previous", fetch=FetchType.LAZY)
//	private StoryNode next;
	
	//Created/Updated 
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")//extra
	private Date createdAt;
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")//extra
	private Date updatedAt;
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}
	
	public StoryNode() {		
	}

	public StoryNode(String blurb, String content, NodeType nType, World world,Clearance clearance) {
		this.blurb = blurb;
		this.content = content;
		this.nType = nType;
		this.world = world;
		this.clearance = clearance;
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
	
//	public StoryNode(String blurb, String content, NodeType nType, World world, Clearance clearance,
//			StoryNode superNode) {
//		this.blurb = blurb;
//		this.content = content;
//		this.nType = nType;
//		this.world = world;//hidden
//		this.clearance = clearance;//hidden?
//		this.superNode = superNode;//hidden
//		this.createdAt = new Date();
//		this.updatedAt = new Date();
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBlurb() {
		return blurb;
	}

	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public NodeType getnType() {
		return nType;
	}

	public void setnType(NodeType nType) {
		this.nType = nType;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

//	public World getHeadofWorld() {
//		return headofWorld;
//	}
//
//	public void setHeadofWorld(World headofWorld) {
//		this.headofWorld = headofWorld;
//	}

	public StoryNode getSuperNode() {
		return superNode;
	}

	public void setSuperNode(StoryNode superNode) {
		this.superNode = superNode;
	}

	public List<StoryNode> getSubNodes() {
		return subNodes;
	}

	public void setSubNodes(List<StoryNode> subNodes) {
		this.subNodes = subNodes;
	}

//	public StoryNode getPrevious() {
//		return previous;
//	}
//
//	public void setPrevious(StoryNode previous) {
//		this.previous = previous;
//	}
//
//	public StoryNode getNext() {
//		return next;
//	}
//
//	public void setNext(StoryNode next) {
//		this.next = next;
//	}

	public int getnodeNum() {
		return nodeNum;
	}

	public void setnodeNum(int nodeNum) {
		this.nodeNum = nodeNum;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Clearance getClearance() {
		return clearance;
	}

	public void setClearance(Clearance clearance) {
		this.clearance = clearance;
	}

	public int getNodeNum() {
		return nodeNum;
	}

	public void setNodeNum(int nodeNum) {
		this.nodeNum = nodeNum;
	}
	
	public List<Chara> getCharacters() {
		return charas;
	}

	public void setCharacters(List<Chara> charas) {
		this.charas = charas;
	}
	
	public void addCharacter(Chara chara) {
		this.charas.add(chara);
	}

	public String toString() {
		if(this.superNode==null) {return blurb;}
		String result = this.nType.getName() + "(" + this.nodeNum + ")";
		StoryNode bigger = superNode;
		if(this.nType.getId()<=4) {
			//example of Book:Collection(1)Series(2)Book(3):Return of the King
			while(bigger!=null) {
				result = bigger.nType.getName() + "(" + bigger.nodeNum + ")" + result;
				bigger = bigger.getSuperNode();
			}
		}else {
			while(bigger.getnType().getId()>1) {//second conditional doesn't include the collection's number
				//Find the nodeType's name=>Collection(1)Series(2)Book(3)Part(4)Chapter(5)Scene(6):Timmy fell down the well
				result = bigger.nType.getName() + "(" + bigger.nodeNum + ")" + result;
				bigger = bigger.getSuperNode();
			}
		}
		result += ": " + this.blurb;
		return result;
	}
}
