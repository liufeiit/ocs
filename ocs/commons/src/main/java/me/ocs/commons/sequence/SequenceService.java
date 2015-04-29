package me.ocs.commons.sequence;

/**
 * 序号服务接口定义类。
 * 
 * @author 刘飞
 * 
 * @version 1.0.0
 * @since 2015年4月27日 下午6:08:15
 */
public interface SequenceService {
	/**
	 * 获得下一个序号
	 * 
	 * @param sequenceType
	 * @param digits
	 * @return
	 */
	String nextValueAsString(String sequenceType, int digits);

	/**
	 * 获得下一个序号，并且自动建立Sequence。
	 * 
	 * @param sequenceType
	 * @param digits
	 * @param startValue
	 *            新建序列时的起始值
	 * @return
	 */
	String nextValueAsStringWithCreate(String sequenceType, int digits, long startValue);

	/**
	 * 获得下一个序号
	 * 
	 * @param sequenceType
	 * @return
	 */
	long nextValue(String sequenceType);

	/**
	 * 获得下N个序号
	 * 
	 * @param sequenceType
	 * @param count
	 * @return
	 */
	long nextValues(String sequenceType, int count);

	/**
	 * 获得下一个序号，并且自动建立Sequence。
	 * 
	 * @param sequenceType
	 * @param digits
	 * @param startValue
	 *            新建序列时的起始值
	 * @return
	 */
	long nextValueWithCreate(String sequenceType, long startValue);

	/**
	 * 获得下N个序号，并且自动建立Sequence。
	 * 
	 * @param sequenceType
	 * @param startValue
	 *            新建序列时的起始值
	 * @param count
	 * @return
	 */
	long nextValuesWithCreate(String sequenceType, long startValue, int count);

	/**
	 * 获得当前序号
	 * 
	 * @param sequenceType
	 * @return
	 */
	long currentValue(String sequenceType);

	/**
	 * 初始化序号
	 * 
	 * @param sequenceType
	 * @param startValue
	 *            新建序列时的起始值
	 */
	void initSequence(String sequenceType, long startValue);
}