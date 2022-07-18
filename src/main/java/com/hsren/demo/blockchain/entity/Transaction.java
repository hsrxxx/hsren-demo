package com.hsren.demo.blockchain.entity;

import com.hsren.demo.blockchain.util.StringUtil;

import java.security.*;
import java.util.ArrayList;

public class Transaction {
	
	public String transactionId; // this is also the hash of the transaction.
	public PublicKey sender; // 发送方地址/公钥。
	public PublicKey reciepient; // 接收方地址/公钥。
	public float value; // 要转移的资金价值/金额
	public byte[] signature; // 这是为了防止其他人花我们钱包里的钱。
	
	public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
	public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
	
	private static int sequence = 0; // 生成了多少事务的粗略统计。
	
	// Constructor
	public Transaction(PublicKey from, PublicKey to, float value,  ArrayList<TransactionInput> inputs) {
		this.sender = from;
		this.reciepient = to;
		this.value = value;
		this.inputs = inputs;
	}
	
	// 计算事务哈希值(将用作其Id)
	private String calulateHash() {
		sequence++; // 增加序列以避免两个相同的事务具有相同的散列
		return StringUtil.applySha256(
				StringUtil.getStringFromKey(sender) +
				StringUtil.getStringFromKey(reciepient) +
				Float.toString(value) + sequence
				);
	}

	// 签名所有的数据，我们不希望被篡改。
	public void generateSignature(PrivateKey privateKey) {
		String data = StringUtil.getStringFromKey(sender) +
				StringUtil.getStringFromKey(reciepient) +
				Float.toString(value);
		signature = StringUtil.applyECDSASig(privateKey,data);
	}
	// 验证我们签名的数据没有被篡改
	public boolean verifiySignature() {
		String data = StringUtil.getStringFromKey(sender) +
				StringUtil.getStringFromKey(reciepient) +
				Float.toString(value);
		return StringUtil.verifyECDSASig(sender, data, signature);
	}
}