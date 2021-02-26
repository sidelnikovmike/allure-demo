package com.propellerads.tests.demo;


import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

public class ManyAttachesTest {



	static Stream<Arguments> dataProvider() {
		List<Arguments> arguments = new ArrayList<>();
		for(int i=0; i < 2000; i++){
			arguments.add(
					of(
							"test_value_" + i
					)
			);
		}
		return arguments.stream();
	}

	@ParameterizedTest(name = "for test value: {0}")
	@MethodSource("dataProvider")
	void testWithParams(String testValue) {
		System.out.println("started test: " + testValue);
		for(int i = 0; i < 100; i++){
			callStep();
			stepWithTextAttach();
		}
	}

	@Step("empty step")
	private void callStep() {

	}

	@Step("step with text attach")
	private void stepWithTextAttach() {
		saveSimpleTextLog("text-log", "тут что-то осмысленное");
	}

	@Attachment(value = "{attachName}", type = "text/plain")
	public static String saveSimpleTextLog(String attachName, String message) {
		if (message == null) {
			message = "null";
		}
		return message;
	}

}
