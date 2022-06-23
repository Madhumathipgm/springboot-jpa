package com.mylearning.springJpa.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Endpoint(id = "features")
@Component
public class FutureEndpointsActuator {
	private final Map<String, Feature> featureMap = new ConcurrentHashMap<>();

	public FutureEndpointsActuator() {
		featureMap.put("Student", new Feature(true));
		featureMap.put("Department", new Feature(false));
		featureMap.put("Authentication", new Feature(false));
	}

	@ReadOperation
	public Map<String, Feature> features() {
		return featureMap;
	}

	public Feature feature(@Selector String featureName) {
		return featureMap.get(featureName);
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	private static class Feature {
		private boolean isEnabled;

	}

}
