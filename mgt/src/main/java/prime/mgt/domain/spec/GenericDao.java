package prime.mgt.domain.spec;

import java.io.Serializable;

public interface GenericDao<T, ID extends Serializable>  {
	
	public void save(T object);
}
