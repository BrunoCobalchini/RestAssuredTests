package com.restassured.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestExamples {

		String baseURI = "http://localhost:3000";
		
		//That's a simples code that use my local API to do some com.restassured.test
		
		@Test
	public void assertGetAll() {

		Response response = RestAssured.get("http://localhost:3000/posts");
		int statusCode = response.getStatusCode();

		System.out.println("Page status: " + statusCode);
		System.out.println("Page Body: " + response.getBody().asString());

		Assert.assertEquals(statusCode,200);
	}

		@Test
	public void assertGet(){

		Response response = RestAssured.get("http://localhost:3000");
		int statusCode = response.getStatusCode();

		System.out.println("Page status: " + statusCode);
		System.out.println("Page Body: " + response.getBody().asString());

		Assert.assertEquals(statusCode,200);

		baseURI = "http://localhost:3000";
		
		given().
			get("/posts").
		then().
			body("[1].id", 
		equalTo(3));
	}

		@Test
	public void assertPost(){

		baseURI = "http://localhost:3000";

		JSONObject request = new JSONObject();

		request.put("title", "Two");
		request.put("author", "One");

		given().
			contentType(ContentType.JSON).
		accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/posts").
		then().
			statusCode(201).log().all();
	}	

		@Test
	public void assertPut(){

		baseURI = "http://localhost:3000";

		JSONObject request = new JSONObject();

		request.put("title", "Test");
		request.put("author", "Three");

		given().
			cookie("id:", "1").
			contentType(ContentType.JSON).
		accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			put("/posts/2").
		then().
			statusCode(200).log().all();
	}	

	@Test
	public void deleteMethod(){

		baseURI = "http://localhost:3000";

		JSONObject request = new JSONObject();

		given().
			cookie("id:", "1").
		header("Content-Type", "aplication/json").
			contentType(ContentType.JSON).
		accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			delete("/posts/3").
		then().
			statusCode(200).log().all();
	}
}