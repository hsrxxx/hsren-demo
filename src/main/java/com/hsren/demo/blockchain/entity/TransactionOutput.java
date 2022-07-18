package com.hsren.demo.blockchain.entity;

import com.hsren.demo.blockchain.util.StringUtil;

import java.security.PublicKey;

public class TransactionOutput {
	public String id;
	public PublicKey reciepient; // 接收方，也就是 硬币 的新主人
	public float value; // 硬币数量
	public String parentTransactionId; // 创建此输出的事务的id
	
	//Constructor
	public TransactionOutput(PublicKey reciepient, float value, String parentTransactionId) {
		this.reciepient = reciepient;
		this.value = value;
		this.parentTransactionId = parentTransactionId;
		this.id = StringUtil.applySha256(StringUtil.getStringFromKey(reciepient)+Float.toString(value)+parentTransactionId);
	}
	
	// 检查硬币是否属于你
	public boolean isMine(PublicKey publicKey) {
		return (publicKey == reciepient);
	}
	
}