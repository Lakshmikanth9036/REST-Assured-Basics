package com.restassured.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.testng.Assert;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class Utility {

	public static String succesStatusCheck(Response response) {
		String resBody = response.getBody().asString();
		System.out.println(resBody);
		String status = response.getBody().jsonPath().getString("status");
		if (status != null)
			Assert.assertEquals("success", status);
		int actual = response.getStatusCode();
		Assert.assertEquals(200, actual);
		return resBody;
	}

	public static List<String> getListFromJsonPath(String json, String xpath) {

		try {
			List<String> outputList = new ArrayList<String>();
			Collection<? extends String> array = JsonPath.read(json, xpath);
			if (array.size() > 0) {
				for (Object obj : array) {
					outputList.add(obj.toString());
				}
			}
			return outputList;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static String getStringFromJsonPath(String json, String xpath) {
		try {

			return JsonPath.read(json, xpath) + "";
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
