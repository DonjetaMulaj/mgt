package prime.mgt.domain.spec;

import java.io.Serializable;

import org.springframework.stereotype.Component;
@Component
public interface GenericDao<T, ID extends Serializable>  {
	
}
