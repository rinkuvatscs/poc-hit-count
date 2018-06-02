package com.hit.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hit.management.domain.HitManagement;
import com.hit.management.service.HitManagementService;

@RestController
@RequestMapping(value = "/hit/v1")
public class HitManagementController {

	@Autowired
	private HitManagementService hitManagementService;

	@RequestMapping(value = "/checkhits")
	public boolean checkHits(String clientId) {
		return hitManagementService.hasLimit(clientId);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public HitManagement addHitManagement(@RequestBody HitManagement hitManagement) {
		return hitManagementService.addHitManagement(hitManagement);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public HitManagement updateHitManagement(@RequestBody HitManagement hitManagement) {
		return hitManagementService.updateHitManagement(hitManagement);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public HitManagement getHitManagement(@RequestParam String clientId) {
		HitManagement hitManagement = hitManagementService.findHitManagmentByClientId(clientId);
		if (hitManagement != null) {
			return hitManagement;
		}
		throw new RuntimeException("Invaild client Id");
	}

	@RequestMapping(value = "/counter", method = RequestMethod.GET)
	public Boolean updateCounter(@RequestParam String clientId) {
		HitManagement hitManagement = hitManagementService.findHitManagmentByClientId(clientId);
		if (hitManagement != null) {
			return true;
		}
		throw new RuntimeException("Invaild client Id");
	}

}
