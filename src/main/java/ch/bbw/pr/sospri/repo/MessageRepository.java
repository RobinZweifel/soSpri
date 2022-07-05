package ch.bbw.pr.sospri.repo;

import ch.bbw.pr.sospri.model.Message;
import org.springframework.data.repository.CrudRepository;
/**
 * MessageRepository
 *
 * @author Robin Zweifel
 * @version 31.5.2022
 */
                                                        //Klasse, id-Typ
public interface MessageRepository extends CrudRepository<Message, Long>{
	//Da wir eine embedded database verwenden, braucht es keine Conecction Information.
}

