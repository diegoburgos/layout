package com.example.demosb.service;

import com.example.demosb.dto.LayoutDTO;
import com.example.demosb.entity.Layout;
import com.example.demosb.exception.LayoutException;
import com.example.demosb.repository.LayoutRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class LayoutService {

	@Value("${layout.size}")
	private int layoutSize;
	@Autowired
	private LayoutRepository layoutRepository;

	public Layout[][] getLayout() {
		Layout[][] ret = new Layout[layoutSize][layoutSize];
		for (Layout l : layoutRepository.findAll()) {
			ret[l.getX()][l.getY()] = l;
		}
		return ret;
	}

	private int idFromCoord(int x, int y) {
		return x * layoutSize + y;
	}

	public Layout setColor(LayoutDTO dto) throws LayoutException {
		Optional<Layout> a = layoutRepository.findById(idFromCoord(dto.getX(), dto.getY()));
		if (a.isEmpty()) {
			throw new LayoutException("Layout for coordinates x:" +
				dto.getX() + " y:" + dto.getY() + " not found");
		}
		Layout layout = a.get();
		layout.setRColor(dto.getRColor());
		layout.setGColor(dto.getGColor());
		layout.setBColor(dto.getBColor());
		layout.setLastActivityDate();
		layoutRepository.save(layout);
		return layout;
	}
}
