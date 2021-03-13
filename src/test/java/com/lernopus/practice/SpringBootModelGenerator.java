package com.lernopus.practice;

import java.io.BufferedReader;  
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.CollectionUtils;

import com.google.common.base.CaseFormat;
/**
 * Created by amernath v on 2020-03-01.
 */
public class SpringBootModelGenerator  
{  
	private static BufferedReader br;

	public static void main(String[] args)   
	{  
		String line = "";  
		String splitBy = ",";  
		String packageName = "package com.jeol.planning.model";
		String inputFileLocation="C:\\Users\\User\\Documents\\Model Input Output\\Transportation Costs.csv";
		String outputLocation="C:\\\\Users\\\\User\\\\Documents\\\\Model Input Output\\Generated File\\PlaTransportationCostsTrn.java";
		String creatorName = "amernavi (Amernath Vinothakrishnan)";
		String createdDate = new Date().toString();
		try   
		{
			br = new BufferedReader(new FileReader(inputFileLocation));
			AtomicInteger index = new AtomicInteger(-1);
			List<String[]> tableMetaDataList = new ArrayList<>();
			while ((line = br.readLine()) != null)  
			{  
				String[] tableMetaData = line.split(splitBy);
				if(index.get() != -1) {
					System.out.println(index.addAndGet(1) + ". Table Column Details [Table Name=" + tableMetaData[0] + ", Column Name=" + tableMetaData[1] + ", Column Type=" + tableMetaData[2] + ", Column Size=" + tableMetaData[3] + "Is Index " + tableMetaData[4] + "Precision " + tableMetaData[5]  + "Scale " + tableMetaData[6] +"]");
					tableMetaDataList.add(tableMetaData);
				} else {
					index.addAndGet(1);
				}
			}  
			StringBuilder fileContent = new StringBuilder(packageName + ";\r\n" + 
					"\r\n" + 
					"import java.sql.Date;\r\n" + 
					"\r\n" + 
					"import javax.persistence.Column;\r\n" + 
					"import javax.persistence.Entity;\r\n" + 
					"import javax.persistence.EntityListeners;\r\n" + 
					"import javax.persistence.Id;\r\n" + 
					"import javax.persistence.IdClass;\r\n" + 
					"import javax.persistence.Table;\r\n" + 
					"\r\n" + 
					"import org.springframework.data.jpa.domain.support.AuditingEntityListener;\r\n" + 
					"\r\n" + 
					"import com.jeol.common.model.Auditable;\r\n" + 
					"\r\n" + 
					"import lombok.AllArgsConstructor;\r\n" + 
					"import lombok.Builder;\r\n" + 
					"import lombok.Data;\r\n" + 
					"import lombok.EqualsAndHashCode;\r\n" + 
					"import lombok.NoArgsConstructor;" + 
					"\r\n" + 
					"/**\r\n" + 
					" * Created by " + creatorName + " on " + createdDate +".\r\n" + 
					" */\r\n" + 
					"@Entity\r\n" + 
					"@Data\r\n" + 
					"@IdClass(TruckFuelEconomyId.class)\r\n" + 
					"@EqualsAndHashCode(callSuper = false)\r\n" + 
					"@EntityListeners(AuditingEntityListener.class)\r\n" + 
					"@AllArgsConstructor\r\n" + 
					"@NoArgsConstructor\r\n" + 
					"@Builder\r\n" + 
					"");
			if(Objects.nonNull(tableMetaDataList) && !CollectionUtils.isEmpty(tableMetaDataList) 
					&& Objects.nonNull(tableMetaDataList.get(0)[0]) && !tableMetaDataList.get(0)[0].isEmpty()) {
				fileContent.append("@Table(name = \" "+ tableMetaDataList.get(0)[0] +"\")\r\n" + 
						"public class " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableMetaDataList.get(0)[0]) + "  extends Auditable<String> {\r\n"); 
			}
			
			tableMetaDataList.stream().forEach(tableMeta -> {
				if(Objects.nonNull(tableMeta) && Objects.nonNull(tableMeta[2]) && !tableMeta[2].isEmpty()) {
					String fieldType = "";
					switch(tableMeta[2]) {
					case "String":
					case "STRING":
					case "string":
						fieldType = "String";
						break;
					case "Integer":
					case "INTEGER":
					case "integer":
						fieldType = "Integer";
						break;
					case "Double":
					case "DOUBLE":
					case "double":
						fieldType = "Double";
						break;
					case "Date":
					case "DATE":
					case "date":
						fieldType = "Date";
						break;
					default:
						break;
					}
					
					fileContent.append("\r\n" + 
							"");
					if(Objects.nonNull(tableMeta[4]) && !tableMeta[4].isEmpty() && tableMeta[4].equalsIgnoreCase("true")) {
						fileContent.append("@Id");
						if(Objects.nonNull(tableMeta[3]) && !tableMeta[3].isEmpty()) {
							if(Objects.nonNull(tableMeta[5]) && !tableMeta[5].isEmpty() && !tableMeta[5].equalsIgnoreCase("-")) {
								if(Objects.nonNull(tableMeta[6]) && !tableMeta[6].isEmpty() && !tableMeta[6].equalsIgnoreCase("-")) {
									fileContent.append("\r\n" +
											"@Column(name = \""+ tableMeta[1] +"\", nullable=false, length="+ Double.valueOf(tableMeta[3]).intValue() + ", precision = " + Double.valueOf(tableMeta[5]).intValue() + ", scale = " + Double.valueOf(tableMeta[6]).intValue() + ")\r\n" + 
											"private "+ fieldType + " " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableMeta[1]) + ";");
								} else {
									fileContent.append("\r\n" +
											"@Column(name = \""+ tableMeta[1] +"\", nullable=false, length="+ Double.valueOf(tableMeta[3]).intValue() + ", precision = " + Double.valueOf(tableMeta[5]).intValue() + ")\r\n" + 
											"private "+ fieldType + " " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableMeta[1]) + ";");	
								}
							} else {
								if(Objects.nonNull(tableMeta[6]) && !tableMeta[6].isEmpty() && !tableMeta[6].equalsIgnoreCase("-")) {
									fileContent.append("\r\n" +
											"@Column(name = \""+ tableMeta[1] +"\", nullable=false, length="+ Double.valueOf(tableMeta[3]).intValue() + ", scale = " + Double.valueOf(tableMeta[6]).intValue() + ")\r\n" + 
											"private "+ fieldType + " " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableMeta[1]) + ";");
								} else {
									fileContent.append("\r\n" +
											"@Column(name = \""+ tableMeta[1] +"\", nullable=false, length="+ Double.valueOf(tableMeta[3]).intValue() + ")\r\n" + 
											"private "+ fieldType + " " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableMeta[1]) + ";");	
								}	
							}
						} else {
							if(Objects.nonNull(tableMeta[5]) && !tableMeta[5].isEmpty() && !tableMeta[5].equalsIgnoreCase("-")) {
								if(Objects.nonNull(tableMeta[6]) && !tableMeta[6].isEmpty() && !tableMeta[6].equalsIgnoreCase("-")) {
									fileContent.append("\r\n" +
											"@Column(name = \""+ tableMeta[1] +"\", nullable=false"  + ", precision = " + Double.valueOf(tableMeta[5]).intValue() + ", scale = " + Double.valueOf(tableMeta[6]).intValue() +  ")\r\n" + 
											"private "+ fieldType + " " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableMeta[1]) + ";");
								} else {
									fileContent.append("\r\n" +
											"@Column(name = \""+ tableMeta[1] +"\", nullable=false"  + ", precision = " + Double.valueOf(tableMeta[5]).intValue() +  ")\r\n" + 
											"private "+ fieldType + " " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableMeta[1]) + ";");
								}
							} else {
								if(Objects.nonNull(tableMeta[6]) && !tableMeta[6].isEmpty() && !tableMeta[6].equalsIgnoreCase("-")) {
									fileContent.append("\r\n" +
											"@Column(name = \""+ tableMeta[1] +"\", nullable=false" + ", scale = " + Double.valueOf(tableMeta[6]).intValue() +  ")\r\n" + 
											"private "+ fieldType + " " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableMeta[1]) + ";");
								} else {
									fileContent.append("\r\n" +
											"@Column(name = \""+ tableMeta[1] +"\", nullable=false)\r\n" + 
											"private "+ fieldType + " " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableMeta[1]) + ";");
								}
							}
						}
						fileContent.append("\r\n" + 
								"");
					}
					else {
						if(Objects.nonNull(tableMeta[3]) && !tableMeta[3].isEmpty()) {
							if(Objects.nonNull(tableMeta[5]) && !tableMeta[5].isEmpty() && !tableMeta[5].equalsIgnoreCase("-")) {
								if(Objects.nonNull(tableMeta[6]) && !tableMeta[6].isEmpty() && !tableMeta[6].equalsIgnoreCase("-")) {
									fileContent.append("\r\n" +
											"@Column(name = \""+ tableMeta[1] +"\", length="+ Double.valueOf(tableMeta[3]).intValue()   + ", precision = " + Double.valueOf(tableMeta[5]).intValue() + ", scale = " + Double.valueOf(tableMeta[6]).intValue() +  ")\r\n" + 
											"private "+ fieldType + " " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableMeta[1]) + ";");
								} else {
									fileContent.append("\r\n" +
											"@Column(name = \""+ tableMeta[1] +"\", length="+ Double.valueOf(tableMeta[3]).intValue() + ", precision = " + Double.valueOf(tableMeta[5]).intValue() + ")\r\n" + 
											"private "+ fieldType + " " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableMeta[1]) + ";");
								}
							} else {
								if(Objects.nonNull(tableMeta[6]) && !tableMeta[6].isEmpty() && !tableMeta[6].equalsIgnoreCase("-")) {
									fileContent.append("\r\n" +
											"@Column(name = \""+ tableMeta[1] +"\", length="+ Double.valueOf(tableMeta[3]).intValue() + ", scale = " + Double.valueOf(tableMeta[6]).intValue() +  ")\r\n" + 
											"private "+ fieldType + " " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableMeta[1]) + ";");
								} else {
									fileContent.append("\r\n" +
											"@Column(name = \""+ tableMeta[1] +"\", length="+ Double.valueOf(tableMeta[3]).intValue() + ")\r\n" + 
											"private "+ fieldType + " " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableMeta[1]) + ";");		
								}
							}
						} else {
							if(Objects.nonNull(tableMeta[5]) && !tableMeta[5].isEmpty() && !tableMeta[5].equalsIgnoreCase("-")) {
								if(Objects.nonNull(tableMeta[6]) && !tableMeta[6].isEmpty() && !tableMeta[6].equalsIgnoreCase("-")) {
									fileContent.append("\r\n" +
											"@Column(name = \""+ tableMeta[1] +"\" "   + ", precision = " + Double.valueOf(tableMeta[5]).intValue() + ", scale = " + Double.valueOf(tableMeta[6]).intValue() +  ")\r\n" + 
											"private "+ fieldType + " " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableMeta[1]) + ";");
								} else {
									fileContent.append("\r\n" +
											"@Column(name = \""+ tableMeta[1] +"\" "   + ", precision = " + Double.valueOf(tableMeta[5]).intValue() + ")\r\n" + 
											"private "+ fieldType + " " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableMeta[1]) + ";");
								}
							} else {
								if(Objects.nonNull(tableMeta[6]) && !tableMeta[6].isEmpty() && !tableMeta[6].equalsIgnoreCase("-")) {
									fileContent.append("\r\n" +
											"@Column(name = \""+ tableMeta[1] +"\" " + ", scale = " + Double.valueOf(tableMeta[6]).intValue() +  ")\r\n" + 
											"private "+ fieldType + " " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableMeta[1]) + ";");
								} else {
									fileContent.append("\r\n" +
											"@Column(name = \""+ tableMeta[1] +"\")\r\n" + 
											"private "+ fieldType + " " + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableMeta[1]) + ";");		
								}
							}
						}
						fileContent.append("\r\n" + 
								"");
					}
					
				}
			});
			System.out.println("######################################################################################################################################################################################################################################");
			System.out.println("------------------------------------------------------ Generated File Content --------------------------------------------------------------------------------------------------------------------------------------------------------");
			fileContent.append("\r\n" + 
					"");
			fileContent.append("\r\n" + 
					"}");
			System.out.println(fileContent.toString());
			
			FileWriter fileWriter = new FileWriter(outputLocation);
			fileWriter.write(fileContent.toString());
			fileWriter.close();
		}   
		catch (IOException e)   
		{  
			e.printStackTrace();  
		} finally {
		} 
	}  
}