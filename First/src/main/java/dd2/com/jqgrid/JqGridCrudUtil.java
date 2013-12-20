package dd2.com.jqgrid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 15
 * Time: 오후 7:00
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("unchecked")
public class JqGridCrudUtil<T, ID extends Serializable> {
    private static final Log logger = LogFactory.getLog(JqGridCrudUtil.class);

    private static final String idName = "id";
    private static final String operName = "oper";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final String idSeparator = ",";
    private static final String compositKeySeparator = "_";

    private Class<T> entityKlass;
    private Class<ID> keyKlass;
    private T instance;
    private Map<String,Object> map;
    private String idValue;
    private String operValue;


    /**
     * Map에서 id를 추출한다.
     */
    private void bindingId() {
        if ( this.map != null ) {
            if ( this.map.containsKey(idName) ) {
                this.idValue = this.map.get(idName).toString();
            }
        }
    }

    /**
     * Map에서 Oper를 추출한다.
     */
    private void bindingOper() {
        if ( this.map != null ) {
            if ( this.map.containsKey(operName) ) {
                this.operValue = this.map.get(operName).toString();
            }
        }
    }

    /**
     * Map의 데이터를 인스턴스에 바인딩 시킨다.
     * @throws IllegalAccessException
     */
    private void bindingFields() throws IllegalAccessException {
        if ( this.map == null )
            throw new NullPointerException("The Map Object is Null.");
        if ( this.entityKlass == null )
            throw new NullPointerException("The Class Object is Null.");

        Field[] fields = entityKlass.getDeclaredFields();
        for (Field field : fields) {
           if (  this.map.containsKey(field.getName()) == true ) {
               field.setAccessible(true);
               Object value = this.map.get(field.getName());
               if ( field.getType() == java.util.Date.class ) {
                   try {
                       value = dateFormat.parse( value.toString() );
                   } catch (ParseException e) {
                       e.printStackTrace();
                   }
               } else if (field.getType() == Long.class ) {
                   value = Long.parseLong(value.toString());
               } else if (field.getType() == Integer.class ) {
                   value = Integer.parseInt(value.toString());
               } else if (field.getType() == Short.class ) {
                   value = Short.parseShort(value.toString());
               } else if (field.getType() == Double.class ) {
                   value = Double.parseDouble(value.toString());
               } else if (field.getType() == Float.class ) {
                   value = Float.parseFloat(value.toString());
               } else if (field.getType() == Boolean.class ) {
                   value = Boolean.parseBoolean(value.toString());
               } else if (field.getType() == String.class ) {
                   // 성능 향상을 위해 주석처리
                   //value = value.toString();
               } else {
                   throw new NoClassDefFoundError("The field of Object is not  primitive type.");
               }
               field.set(instance, value);
            }
        }
    }

    /**
     * jqGrid에 설정된 ids
     * @return
     */
    private String[] getStrIds() {
        return idValue.split(idSeparator);
    }

    /**
     * ID 중에 첫번째 id를 리턴한다.
     * @return
     */
    private String getStrId() {
        return getStrIds()[0];
    }

    /**
     * 단일키 아이디
     * @return
     */
    private ID getInnerId(String strId) {
        if ( this.map == null )
            throw new NullPointerException("The Map Object is Null.");
        if ( this.keyKlass == null )
            throw new NullPointerException("The KeyClass Object is Null.");
        if ( strId == null || strId.equals("") ) {
            throw new NullPointerException("The ID String is Null.");
        }

        ID id = null;
        try {
            id = keyKlass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if ( keyKlass == java.util.Date.class ) {
            try {
                id = (ID)dateFormat.parse( strId );
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (keyKlass == Long.class ) {
            id = (ID)new Long(Long.parseLong(strId));
        } else if (keyKlass == Integer.class ) {
            id = (ID)new Integer(Integer.parseInt(strId));
        } else if (keyKlass == Short.class ) {
            id = (ID)new Short(Short.parseShort(strId));
        } else if (keyKlass == Double.class ) {
            id = (ID)new Double(Double.parseDouble(strId));
        } else if (keyKlass == Float.class ) {
            id = (ID)new Float(Float.parseFloat(strId));
        } else if (keyKlass == Boolean.class ) {
            id = (ID)new Boolean(Boolean.parseBoolean(strId));
        } else if (keyKlass == String.class ) {
            id = (ID)strId;
        } else {
            throw new NoClassDefFoundError("The field of Object is not  primitive type.");
        }

        return id;
    }

    /**
     * 복합키 아이디
     * @param compositeKeyNames
     * @return
     */
    private ID getInnerId(String[] compositeKey, String[] compositeKeyNames) {
        if ( this.keyKlass == null )
            throw new NullPointerException("The Class Object is Null.");

        ID id = null;
        try {
            id = keyKlass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Field[] fields = keyKlass.getDeclaredFields();

        for (Field field : fields) {
            for(int idx = 0; idx < compositeKeyNames.length; idx++ ) {
                String fieldName = compositeKeyNames[idx];

                if ( fieldName.equals(field.getName()) ) {
                    field.setAccessible(true);
                    Object value = null;
                    if ( field.getType() == java.util.Date.class ) {
                        try {
                            value = dateFormat.parse( compositeKey[idx] );
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else if (field.getType() == Long.class ) {
                        value = Long.parseLong(compositeKey[idx]);
                    } else if (field.getType() == Integer.class ) {
                        value = Integer.parseInt(compositeKey[idx]);
                    } else if (field.getType() == Short.class ) {
                        value = Short.parseShort(compositeKey[idx]);
                    } else if (field.getType() == Double.class ) {
                        value = Double.parseDouble(compositeKey[idx]);
                    } else if (field.getType() == Float.class ) {
                        value = Float.parseFloat(compositeKey[idx]);
                    } else if (field.getType() == Boolean.class ) {
                        value = Boolean.parseBoolean(compositeKey[idx]);
                    } else if (field.getType() == String.class ) {
                        value = compositeKey[idx];
                    } else {
                        throw new NoClassDefFoundError("The field of Object is not  primitive type.");
                    }

                    try {
                        field.set(id, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    continue;
                }
            }
        }

        return id;
    }

    /**
     * 단일키를 반환한다.
     * @return
     */
    public ID getId() {
        return getInnerId(getStrId());
    }

    /**
     * 단일키 배열을 반환한다.
     * @return
     */
    public ID[] getIds() {
        String[] ids = getStrIds();
        ID[] keys = (ID[])Array.newInstance(keyKlass, ids.length);

        for (int idx = 0; idx < keys.length; idx++ ) {
            keys[idx] = getInnerId(ids[idx]);
        }
        return keys;
    }

    /**
     * 복합키를 반환한다.
     * @param compositeKeyNames
     * @return
     */
    public ID getId(String[] compositeKeyNames) {
        String[] compositeKeys = getStrId().split(compositKeySeparator);
        return getInnerId(compositeKeys, compositeKeyNames);
    }

    /**
     * 복합키 배열을 반환한다.
     * @param compositeKeyNames
     * @return
     */
    public ID[] getIds(String[] compositeKeyNames) {
        String[] ids = getStrIds();
        ID[] keys = (ID[])Array.newInstance(keyKlass, ids.length);

        for (int idx = 0; idx < keys.length; idx++ ) {
            String[] compositeKeys = ids[idx].split(compositKeySeparator);
            if ( compositeKeys.length == compositeKeyNames.length ) {
                keys[idx] =  getInnerId(compositeKeys, compositeKeyNames);
            }
        }
        return keys;
    }

    /**
     * 생성자
     * @param entityKlass
     * @param keyKlass
     * @param map
     */
    public JqGridCrudUtil(Class<T> entityKlass, Class<ID> keyKlass, Map<String, Object> map) {
        this.entityKlass = entityKlass;
        this.keyKlass = keyKlass;
        this.map = map;
        bindingId();
        bindingOper();
    }

    public Map<String,Object> getMap() {
        return map;
    }

    public void setMap(Map<String,Object> map) {
        this.map = map;
    }

    /**
     * add, edit, del의 Operation을 얻는다.
     * @return
     */
    public JQGRID_OPER getOper() {
        JQGRID_OPER ret = JQGRID_OPER.NONE;
        switch (this.operValue) {
            case "add":
                ret = JQGRID_OPER.ADD;
                break;
            case "edit":
                ret = JQGRID_OPER.EDIT;
                break;
            case "del":
                ret = JQGRID_OPER.DELETE;
                break;
        }
        return ret;
    }

    public T creteEntity() {
        try {
            instance = entityKlass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * Map의 값으로 바인딩 된 객체를 생성한다.
     * @return
     */
    public T createEntityWithDataBinding() {
        creteEntity();
        try {
            bindingFields();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }
}
