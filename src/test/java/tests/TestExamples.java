package tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

import java.net.URI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TestExamples {

		String baseURI = "http://localhost:3000";
		
//		@Test
	public void test1() {

		Response response = RestAssured.get("http://localhost:3000/posts");
		int statusCode = response.getStatusCode();

		System.out.println("Page status: " + statusCode);
		System.out.println("Page Body: " + response.getBody().asString());

		Assert.assertEquals(statusCode,200);
	}

//		@Test
	public void test2(){

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

//		@Test
	public void test3(){

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

//		@Test
	public void test4(){

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

//	@Test
	public void test5(){

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