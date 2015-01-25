package net.p2pmag.totl.dao;
import java.util.List;

import net.p2pmag.totl.domain.*;

public interface TodoTaskDAO extends DAO<TodoTask, Integer> {
	public List<TodoTask> findAllByList( Integer list_id );
	public void delete(Integer id);
}
