package com.hsren.demo.blockchain.entity;

import com.hsren.demo.blockchain.util.StringUtil;
import lombok.Data;

import java.util.Date;

/**
 * 区块
 */
@Data
public class Block {

	// 当前区块的哈希值
	public String hash;
	// 前一个区块的哈希值
	public String previousHash;
	// 当前区块的数据
	private String data; //our data will be a simple message.
	// 当前区块的时间戳
	private long timeStamp; //as number of milliseconds since 1/1/1970.

	private int nonce;

	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();
	}

	// 计算哈希值
	public String calculateHash() {
		return StringUtil.applySha256(
			previousHash + timeStamp + nonce + data
		);
	}

	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
}