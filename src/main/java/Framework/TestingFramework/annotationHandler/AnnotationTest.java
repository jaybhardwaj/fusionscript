package Framework.TestingFramework.annotationHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import Framework.TestingFramework.utils.ReadEvironmentProperties;

public class AnnotationTest implements IAnnotationTransformer {
	Properties props = new Properties();
	public static Map<String, String> envProperties=ReadEvironmentProperties.getEnvironmentProperties();
	Map<String, Integer> map = new HashMap();
	public static Set<String> utilities = new HashSet<String>();
	public static String filename = null;
	int index=0;
	boolean run = true;
	
	public void transform(ITestAnnotation annotation, Class testClass,Constructor testConstructor, Method testMethod) {
		filename = envProperties.get("suite");
		File file = new File("src//main//java//Framework//TestingFramework//testCasesExecutionHandler//"+envProperties.get("suite").toLowerCase()+".properties");
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine() && run == true) {
				String s = sc.next();
				if(s.contains("TC")){  
		           	String[] str = s.split("=");
		           	map.put(str[0], index);
		           	index++;
		        }
			}
			sc.close();
		    run = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
		try {
			filename = "sanity";
			props.load(new FileInputStream("src//main//java//Framework//TestingFramework//testCasesExecutionHandler//"+envProperties.get("suite").toLowerCase()+".properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String str = testMethod.getDeclaringClass().getSimpleName();
		if(props.containsKey(str) && props.get(str).equals("true")){
			if(props.containsKey(annotation.getDescription())){
				if(props.get(annotation.getDescription()).equals("true")){
					System.out.println(annotation.getDescription()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+map.get(annotation.getDescription()));
					annotation.setEnabled(true);
					try{
						annotation.setPriority(map.get(annotation.getDescription()));
					} catch(Exception e) {
						annotation.setPriority(1);
					}
					//System.out.println( testMethod.getDeclaringClass().getSimpleName());
					utilities.add(str);
					//System.out.println(annotation.getDescription()+"()()()()()()()========="+annotation.getPriority());
				} else if(props.get(annotation.getDescription()).equals("false")){
					annotation.setEnabled(false);
				} else {
					annotation.setEnabled(false);
				}
			} else {
				annotation.setEnabled(false);
			}
		} else {
			annotation.setEnabled(false);
		}
	}

}
