package br.ce.wcaquino.taskbackend.controller;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {
	
	@Mock
	private TaskRepo taskRepo;
	
	@InjectMocks
	private TaskController controller;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void ShouldNotSaveTaskWithouDescription(){
		Task todo = new Task();
		todo.setDueDate(LocalDate.now());
		try {
			controller.save(todo);
			Assert.fail("Nao deveria chegar nesse ponto");
		} catch (Exception e) {
			Assert.assertEquals("Fill the task description", e.getMessage());
		}
	}
	
	@Test
	public void ShouldNotSaveTaskWithouDate(){
		Task todo = new Task();
		todo.setTask("Description");
		try {
			controller.save(todo);
			Assert.fail("Nao deveria chegar nesse ponto");
		} catch (Exception e) {
			Assert.assertEquals("Fill the due date", e.getMessage());
		}
	}
	
	@Test
	public void ShouldNotSaveTaskWithPastDate(){
		Task todo = new Task();
		todo.setTask("Description");
		todo.setDueDate(LocalDate.of(2010, 03, 10));
		try {
			controller.save(todo);
			Assert.fail("Nao deveria chegar nesse ponto");
		} catch (Exception e) {
			Assert.assertEquals("Due date must not be in past", e.getMessage());
		}
	}

	@Test
	public void ShouldNotSaveTaskWithSucess() throws ValidationException{
		Task todo = new Task();
		todo.setTask("Description");
		todo.setDueDate(LocalDate.now());
		controller.save(todo);
		
		Mockito.verify(taskRepo).save(todo);
	}
	
}
