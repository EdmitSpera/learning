### 字符与字符串
```java
public char charAt(int index)
public char[] toCharArray()
```
### 比较
```java
public boolean equals(Object anObject)
public boolean equalsIgnoreCase(String anotherString)   //不区分大小写

public int compareTo(String anotherString)  // 比较两个字符串大小
public int compareToIgnoreCase(String str)
```
### 查找
```java
public boolean contains(CharSequence s) // CharSequence接口声明了可变字符串

public int indexOf(String str)  //获取索引
public int indexOf(String str,int fromIndex)
    
public int lastIndexOf(String str)  // 从后向前找字串索引
public int lastIndexOf(String str,int fromIndex)

public boolean startsWith(String prefix, int toffset)
public boolean endsWith(String suffix)
```
### 截取
```java
public String substring(int beginIndex)
public String substring(int beginIndex, int endIndex)   // 不包含endIndex
```
### 替换
```java
public String replace(char oldChar, char newChar)
public String replaceAll(String regex,String replacement)

```
### 拆分
```java
public String[] split(String regex)
public String[] split(String regex,int limit)
```
### 其他操作方法
```java
public int length()
public boolean isEmpty()

public String toUpperCase()
public String toLowerCase()
public String concat(String str)
public String trim()
```