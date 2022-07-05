package ch.bbw.pr.sospri.service;

import ch.bbw.pr.sospri.repo.MessageRepository;
import ch.bbw.pr.sospri.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * MessageService
 *
 * @author Robin Zweifel
 * @version 31.5.2022
 */
@Service
public class MessageService {
	@Autowired
	private MessageRepository repository;

	Logger logger = LoggerFactory.getLogger(MessageService.class);
	
	public Iterable<Message> getAll(){
		logger.debug("getAll");
		return repository.findAll();
	}

	public void add(Message message) {
		logger.debug("add " + message);
		repository.save(message);
	}

	public void update(Long id, Message message) {
		logger.debug("update " + id + " " + message);
		repository.save(message);
	}

	public void deleteById(Long id) {
		logger.debug("deleteById " + id);
		repository.deleteById(id);
	}
}
