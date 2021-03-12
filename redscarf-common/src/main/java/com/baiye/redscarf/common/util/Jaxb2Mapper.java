package com.baiye.redscarf.common.util;

import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ConcurrentReferenceHashMap;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * jaxb映射器.
 *
 * @author 白也
 * @date 2021/1/19 5:25 下午
 */
public class Jaxb2Mapper {

    private final Map<Class<?>, JAXBContext> jaxbContexts = new ConcurrentReferenceHashMap<Class<?>, JAXBContext>(64);

    /**
     * 将XML格式的字符串转换为对象.
     */
    public <T> T unmarshal(String xml, Class<T> clazz) {
        try {
            Unmarshaller unmarshaller = createUnmarshaller(clazz);
            JAXBElement<T> jaxbElement = unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), clazz);
            return jaxbElement.getValue();
        } catch (UnmarshalException ex) {
            throw new RuntimeException("Could not unmarshal to [" + clazz + "]: " + ex.getMessage(), ex);
        } catch (JAXBException ex) {
            throw new RuntimeException("Could not instantiate JAXBContext: " + ex.getMessage(), ex);
        }
    }

    /**
     * 将对象转换为XML格式的字符串(使用默认编码).
     */
    public String marshal(Object instance) {
        return marshal(instance, null);
    }

    /**
     * 将对象转换为XML格式的字符串.
     */
    public String marshal(Object instance, Charset charset) {
        try {
            Class<?> clazz = ClassUtils.getUserClass(instance);
            Marshaller marshaller = createMarshaller(clazz);
            setCharset(marshaller, charset);
            Writer writer = new StringWriter();
            marshaller.marshal(instance, new StreamResult(writer));
            return writer.toString();
        } catch (MarshalException ex) {
            throw new RuntimeException("Could not marshal [" + instance + "]: " + ex.getMessage(), ex);
        } catch (JAXBException ex) {
            throw new RuntimeException("Could not instantiate JAXBContext: " + ex.getMessage(), ex);
        }
    }

    /**
     * Creates a new {@link Marshaller} for the given class.
     */
    protected Marshaller createMarshaller(Class<?> clazz) {
        try {
            JAXBContext jaxbContext = getJaxbContext(clazz);
            return jaxbContext.createMarshaller();
        } catch (JAXBException ex) {
            throw new RuntimeException("Could not create Marshaller for class [" + clazz + "]: " + ex.getMessage(), ex);
        }
    }

    /**
     * Creates a new {@link Unmarshaller} for the given class.
     *
     * @param clazz
     * @return
     * @throws RuntimeException
     */
    protected Unmarshaller createUnmarshaller(Class<?> clazz) {
        try {
            JAXBContext jaxbContext = getJaxbContext(clazz);
            return jaxbContext.createUnmarshaller();
        } catch (JAXBException ex) {
            throw new RuntimeException("Could not create Unmarshaller for class [" + clazz + "]: " + ex.getMessage(), ex);
        }
    }

    /**
     * Returns a {@link JAXBContext} for the given class.
     *
     * @param clazz
     * @return
     * @throws RuntimeException
     */
    protected JAXBContext getJaxbContext(Class<?> clazz) {
        Assert.notNull(clazz, "'clazz' must not be null");
        JAXBContext jaxbContext = jaxbContexts.get(clazz);
        if (jaxbContext == null) {
            try {
                jaxbContext = JAXBContext.newInstance(clazz);
                jaxbContexts.put(clazz, jaxbContext);
            } catch (JAXBException ex) {
                throw new RuntimeException("Could not instantiate JAXBContext for class [" + clazz + "]: " + ex.getMessage(), ex);
            }
        }
        return jaxbContext;
    }

    private void setCharset(Marshaller marshaller, Charset charset) throws PropertyException {
        if (charset != null) {
            marshaller.setProperty(Marshaller.JAXB_ENCODING, charset.name());
        }
    }
}
