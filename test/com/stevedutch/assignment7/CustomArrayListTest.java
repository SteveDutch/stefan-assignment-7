package com.stevedutch.assignment7;

import static org.junit.Assert.assertEquals;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.coderscampus.arraylist.CustomList;

class CustomArrayListTest {

	@Test
	void testAdd() {
		CustomList<String> sut = new CustomArrayList<>();
		sut.add("a first test");

		boolean exspectedResult = sut.add("2nd String");

		assertEquals("2nd String", sut.get(1));
		assertEquals(true, exspectedResult);
	}

	/*
	 * Tests for boolean add (int index, T item) throws IndexOutOfBoundsException;
	 */

	@Test
	void should_add_element_at_given_index_and_return_true() {
		CustomList<String> sut = new CustomArrayList<>();
		for (int j = 0; j < 10; j++) {
			String elem = "test element " + String.valueOf(j);
			sut.add(elem);
		}

		boolean exspectedBoolResult = sut.add(9, "new test element no 1");
		String exspectedtResult = sut.get(9);
		sut.remove(9);
		sut.add(9, "new test element no 2");
		String exspectedtResult2 = sut.get(9);

		assertEquals("new test element no 1", exspectedtResult);
		assertEquals(true, exspectedBoolResult);
		assertEquals("new test element no 2", exspectedtResult2);

	}

	@Test
	void should_add_element_at_given_last_index_after_earlier_remove() {
		CustomList<String> sut = new CustomArrayList<>();
		for (int j = 0; j < 10; j++) {
			String elem = "test element " + String.valueOf(j);
			sut.add(elem);
		}

		sut.remove(9);
		sut.add(9, "new test element no 2");
		String exspectedtResult2 = sut.get(9);

		assertEquals("new test element no 2", exspectedtResult2);

	}

	@Test
	void should_shuffle_other_items_to_the_right() {
		CustomList<String> sut = new CustomArrayList<>();
		for (int j = 0; j < 7; j++) {
			String elem = "test element " + String.valueOf(j);
			sut.add(elem);
		}

		sut.add(4, "new element");
		String exspectedResult = sut.get(6);

		assertEquals("test element 5", exspectedResult);
	}

	@Test
	void should_always_set_size_to_number_of_actual_stored_elements()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		CustomList<String> sut = new CustomArrayList<>();

		for (int j = 0; j < 7; j++) {
			String elem = "test element " + String.valueOf(j);
			sut.add(elem);
		}

		// use of reflection
		sut.add(4, "new element");
		Field field = null;
		field = sut.getClass().getDeclaredField("size");
		field.setAccessible(true);
		int exspectedResult = field.getInt(sut);

		assertEquals(8, exspectedResult);

	}

	@Test
	void should_throw_exception_if_index_is_out_of_array() {
		CustomList<String> sut = new CustomArrayList<>();
		IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> {
			sut.add(99, "an index to make the function throw");
		});

		assertEquals("Ooops... here's the Index out Of Bounds because your index is too big for the array ",
				exception.getMessage());

	}

	@Test
	void should_throw_exception_if_index_is_bigger_than_number_of_elements() {
		CustomList<String> sut = new CustomArrayList<>();
		for (int j = 0; j < 7; j++) {
			String elem = "test element " + String.valueOf(j);
			sut.add(elem);
		}

		IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> {
			sut.add(8, "an index to make the function throw");
		});

		assertEquals("Ooops... here's the Index out Of Bounds because your index is too big for the array ",
				exception.getMessage());

	}

	@Test
	void testGetSize() {
		CustomList<String> sut = new CustomArrayList<>();
		sut.add("a first test");
		sut.add("2nd String");

		int exspectedResult = sut.getSize();

		assertEquals(2, exspectedResult);
	}

	@Test
	void testGet() {
		CustomList<String> sut = new CustomArrayList<>();
		sut.add("a first test");
		sut.add("2nd String");
		sut.add("third");

		String exspectedtResult = sut.get(0);
		assertEquals("a first test", exspectedtResult);

		IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> {
			sut.get(99);
		});
		assertEquals("Ooops... Index out Of Bounds because your index isn't part of the array ;)---",
				exception.getMessage());
	}

	/*
	 * tests for T remove(int index) throws IndexOutOfBoundsException;
	 */

	@Test
	void should_return_T() {
		CustomList<String> sut = new CustomArrayList<>();

		sut.add("a first test");
		sut.add("2nd String");
		sut.add("third");
		String exspectedResult = sut.remove(1);

		assertEquals("2nd String", exspectedResult);
	}

	@Test
	void should_remove_item_and_shuffle_leftsided_items_leftwards() {
		CustomList<String> sut = new CustomArrayList<>();
		sut.add("a first test");
		sut.add("2nd String");
		sut.add("third");
		sut.add("four");

		sut.remove(1);

		String exspectedtResult = sut.get(1);
		String exspectedtResultTwo = sut.get(2);

		assertEquals("third", exspectedtResult);
		assertEquals("four", exspectedtResultTwo);

	}

	@Test
	void should_throw_IndexOutOfBoundsException_when_index_bigger_than_length() {
		CustomList<String> sut = new CustomArrayList<>();
		IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> {
			sut.remove(99);
		});
		assertEquals("Ooops...your removal lead into Index out Of Bounds or so ;)---", exception.getMessage());
	}

	@Test
	void should_throw_Exception_when_index_biggger_than_number_of_stored_elements() {
		CustomList<String> sut = new CustomArrayList<>();

		for (int j = 0; j < 7; j++) {
			String elem = "test element " + String.valueOf(j);
			sut.add(elem);
		}

		IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> {
			sut.remove(7);
		});

		assertEquals("Ooops...your chosen index was out of bounds", exception.getMessage());

	}

}
