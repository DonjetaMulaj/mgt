package prime.mgt.api.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author Isah Bllaca <isah.bllaca@asseco-see.com>
 *
 */
@Component
public class JaxbFactory {
	
	private static final Logger logger = LogManager.getLogger(JaxbFactory.class);
	
    public Unmarshaller createUnmarshaller(Class<?> classesToBeBound) {
        JAXBContext jaxbContext;
        Unmarshaller unmarshaller = null;
        try {
            jaxbContext = JAXBContext.newInstance(classesToBeBound);
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            logger.error("Error while tring to create Unmarshaller.", e);
        }
        return unmarshaller;
    }

    public Marshaller createMarshaller(Class<?> classesToBeBound) {
        JAXBContext jaxbContext;
        Marshaller marshaller = null;
        try {
            jaxbContext = JAXBContext.newInstance(classesToBeBound);
            marshaller = jaxbContext.createMarshaller();
        } catch (JAXBException e) {
        	logger.error("Error while tring to create Marshaller.", e);
        }
        return marshaller;
    }
}
