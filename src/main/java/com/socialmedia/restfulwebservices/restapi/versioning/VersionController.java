package com.socialmedia.restfulwebservices.restapi.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
	
	@GetMapping("/V1/person")
	public PersonV1 getNameV1() {
		return new PersonV1("Jane Doe");
	}
	
	@GetMapping("/V2/person")
	public PersonV2 getNameV2() {
		return new PersonV2(new Name("Jane","Doe"));
	}
	
	@GetMapping(path = "/person", headers="version=1")
	public PersonV1 getNameHeader() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path="/person", headers="version=2")
	public PersonV2 getNameHeaderV2() {
		return new PersonV2(new Name("Bob","Charlie"));
	}

}
