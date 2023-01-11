package com.in28minutes.restful.web.services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPerson {

	// URI Versioning
	@GetMapping("/v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping("/v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	// Request Parameter Versioning
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getFirstVersionOfPersonRequestParams() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getSecondVersionOfPersonRequestParams() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	// Custom Headers Versioning
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonRequestHeader() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonRequestHeader() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	// Media type Versioning a.k.a content negotiation or accept header
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeader() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeader() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
}