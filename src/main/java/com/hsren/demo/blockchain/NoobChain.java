package com.hsren.demo.blockchain;

import java.security.Security;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import com.hsren.demo.blockchain.entity.Block;
import com.hsren.demo.blockchain.entity.Transaction;
import com.hsren.demo.blockchain.entity.Wallet;
import com.hsren.demo.blockchain.util.StringUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class NoobChain {

	// 初始化挖矿难度
	public static int difficulty = 6;

	// 区块列表（区块链）
	public static ArrayList<Block> blockchain = new ArrayList<Block>();

	public static Wallet walletA;
	public static Wallet walletB;

	public static void main(String[] args) {
		// 将 BouncyCastle 设置为安全提供商
		Security.addProvider(new BouncyCastleProvider());
		// 创建新的钱包
		walletA = new Wallet();
		walletB = new Wallet();
		// 测试公钥和私钥
		System.out.println("Private and public keys:");
		System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
		System.out.println(StringUtil.getStringFromKey(walletA.publicKey));
		// 创建一个从 WalletA 到 walletB 的测试事务
		Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
		transaction.generateSignature(walletA.privateKey);
		// 验证签名是否有效，并从公钥验证
		System.out.println("Is signature verified");
		System.out.println(transaction.verifiySignature());


		// 将block添加到区块链数组列表中
//		blockchain.add(new Block("第一块", "0"));
//		System.out.println("Trying to Mine 第一块... ");
//		blockchain.get(0).mineBlock(difficulty);
//
//		blockchain.add(new Block("第二块",blockchain.get(blockchain.size()-1).hash));
//		System.out.println("Trying to Mine 第二块... ");
//		blockchain.get(1).mineBlock(difficulty);
//
//		blockchain.add(new Block("第三块",blockchain.get(blockchain.size()-1).hash));
//		System.out.println("Trying to Mine 第三块... ");
//		blockchain.get(2).mineBlock(difficulty);
//
//		blockchain.add(new Block("第四块",blockchain.get(blockchain.size()-1).hash));
//		System.out.println("Trying to Mine 第四块... ");
//		blockchain.get(3).mineBlock(difficulty);
//
//		System.out.println("\nBlockchain is Valid: " + isChainValid());
//
//		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
//		System.out.println("\nThe block chain: ");
//		System.out.println(blockchainJson);

		// 修改区块链数据
//		isChainValid();
//		Block block = new Block("第二块", blockchain.get(0).hash);
//		blockchain.set(1,block);
//		isChainValid();


	}

	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');

		// 循环区块链校验哈希值
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);

			// 比较注册时的哈希值 和 计算的哈希值
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("当前区块哈希值不正确！");
				return false;
			}
			// 比较前区块的哈希值 和 当前区块的前哈希值
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("前区块哈希值不正确！");
				return false;
			}
			// 检查哈希值是否已算出
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}

}