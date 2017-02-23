package es.cic.curso.curso18.ejercicio026.backend.repository;

import java.io.Serializable;

public interface Identificable<K> extends Serializable {

	K getId();

	void setId(K id);

}