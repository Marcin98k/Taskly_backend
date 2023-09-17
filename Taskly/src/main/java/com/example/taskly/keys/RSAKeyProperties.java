package com.example.taskly.keys;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RSAKeyProperties {

	private RSAPublicKey publicKey;
	private RSAPrivateKey privateKey;
	
	public RSAKeyProperties() {
		KeyPair keyPair = UserKeyGenerator.generateKey();
		publicKey = (RSAPublicKey) keyPair.getPublic();
		privateKey = (RSAPrivateKey) keyPair.getPrivate();
	}
	
	public RSAPublicKey getPublicKey() {
		return publicKey;
	}
	
	public void setPublicKey(RSAPublicKey publicKey) {
		this.publicKey = publicKey;
	}
	
	public RSAPrivateKey getPrivateKey() {
		return privateKey;
	}
	
	public void setPrivateKey(RSAPrivateKey privateKey) {
		this.privateKey = privateKey;
	}
}
