package com.hsren.demo.blockchain.entity;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class Wallet {
	public PrivateKey privateKey;
	public PublicKey publicKey;

	public Wallet(){
		generateKeyPair();
	}

	public void generateKeyPair() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA","BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
			// 初始化密钥生成器并生成KeyPair
			keyGen.initialize(ecSpec, random);   // 256字节提供了可接受的安全级别
			KeyPair keyPair = keyGen.generateKeyPair();
			// 从keyPair设置公钥和私钥
			privateKey = keyPair.getPrivate();
			publicKey = keyPair.getPublic();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}