package pgdp.pingulib.datastructures.trees;

import java.util.LinkedList;
import java.util.List;

public class BST<T extends Comparable<T>> {

	private RW lock;
	private BSTNode<T> root;

	public BST() {
		root = null;
		lock = new RW();
	}

	public boolean isEmpty() throws InterruptedException {
		lock.startRead();
		boolean result = root == null;
		lock.endRead();
		return result;
	}

	public int size() throws InterruptedException {
		lock.startRead();
		int result = root == null ? 0 : root.size();
		/*if (root == null) {
			return 0;
		}
		return root.size();*/
		lock.endRead();
		return result;
	}

	public boolean contains(T value) throws InterruptedException {
		lock.startRead();
		boolean result;
		if (root == null) {
			result = false;
		}
		else {
			result = root.contains(value);
		}
		/*if (isEmpty()) {
			return false;
		}
		return root.contains(value);*/
		lock.endRead();
		return result;
	}

	public T get(T value) throws InterruptedException {
		lock.startRead();
		T result;
		if (root == null) {
			result = null;
		}
		else {
			result = root.get(value);
		}
		/*if (isEmpty()) {
			return null;
		}
		return root.get(value);*/
		lock.endRead();
		return result;
	}

	public boolean insert(T value) throws InterruptedException {
		lock.startWrite();
		boolean result;
		if (value == null) {
			result = false;
		}
		else if (root == null) {
			root = new BSTNode<T>(value);
			result = true;
		} else {
			result = root.insert(value);
		}
		/*if (value == null) {
			return false;
		}
		if (isEmpty()) {
			root = new BSTNode<T>(value);
			return true;
		} else {
			return root.insert(value);
		}*/
		lock.endWrite();
		return result;
	}

	public List<T> toList() throws InterruptedException {
		lock.startRead();
		List<T> result;
		if (root == null) {
			result = new LinkedList<>();
		}
		else {
			result = root.toList(Order.IN);
		}
		/*if (isEmpty()) {
			return new LinkedList<>();
		}
		return root.toList(Order.IN);*/
		lock.endRead();
		return result;
		/*if (isEmpty()) {
			return new LinkedList<>();
		}
		return root.toList(Order.IN);*/
	}

	@Override
	public String toString() {

		try {
			lock.startRead();
			String result;
			if (root == null) {
				result = "[]";
			} else {
				result = root.toString();
			}
			lock.endRead();
			return result;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}

		/*try {
			if (isEmpty()) {
				return "[]";
			} else {
				return root.toString();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}*/
	}
}
