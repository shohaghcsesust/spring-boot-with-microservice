package com.in28minutes.restful.web.services.filtering;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		Set<String> fieldToBeFiltered = new HashSet<>(Arrays.asList("field1", "field3"));
		applyFilter(mappingJacksonValue, fieldToBeFiltered);
		return mappingJacksonValue;
	}

	private void applyFilter(MappingJacksonValue mappingJacksonValue, Set<String> fieldToBeFiltered) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fieldToBeFiltered);
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue filteringList() {
		List<SomeBean> asList = Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value4", "value5", "value6"));
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(asList);
		Set<String> fieldToBeFiltered = new HashSet<>(Arrays.asList("field2", "field3"));
		applyFilter(mappingJacksonValue, fieldToBeFiltered);
		return mappingJacksonValue;
	}
}