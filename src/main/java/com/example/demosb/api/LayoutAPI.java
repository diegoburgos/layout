package com.example.demosb.api;

import com.example.demosb.dto.LayoutDTO;
import com.example.demosb.dto.MessageDTO;
import com.example.demosb.entity.Layout;
import com.example.demosb.exception.LayoutException;
import com.example.demosb.service.LayoutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path="/api/v1/layout", produces = "application/json")
public class LayoutAPI {

	@Autowired
	LayoutService layoutService;

	@GetMapping("")
	@ResponseBody
	public ResponseEntity<Layout[][]> getLayout() {
		return new ResponseEntity(layoutService.getLayout(), HttpStatus.OK);
	}

	@PutMapping("/reset")
	@ResponseBody
	public ResponseEntity<MessageDTO> reset() {
		return new ResponseEntity<>(new MessageDTO("Layout restarted"), HttpStatus.OK);
	}

	@PostMapping("/set")
	@ResponseBody
	public ResponseEntity<Layout> setColor(@RequestBody LayoutDTO layoutDTO) {
		try {
			Layout ret = layoutService.setColor(layoutDTO);
			return new ResponseEntity<>(ret, HttpStatus.OK);
		} catch (LayoutException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
