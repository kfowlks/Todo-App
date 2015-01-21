package net.p2pmag.totl.dao;
import java.util.List;

import net.p2pmag.totl.domain.*;

public interface TodoListDAO extends DAO<TodoList, Integer> {
	public List<TodoList> getAllOrgs();
}
