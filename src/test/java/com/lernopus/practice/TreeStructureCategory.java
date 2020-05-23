package com.lernopus.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TreeStructureCategory {
	public static void main(String args[]) {
		List<Map<String,String>> treeList = new ArrayList<>();
		Map<String,String> treeMap = new HashMap<>();
		treeMap.put("categoryId", "1");
		treeMap.put("parentCactegoryId", "1");
		treeMap.put("rootCategoryId", "1");
		treeMap.put("categoryName", "LearnOpus Official Category");
		treeList.add(treeMap);
		
		Map<String,String> treeMap1 = new HashMap<>();
		treeMap1.put("categoryId", "2");
		treeMap1.put("parentCactegoryId", "2");
		treeMap1.put("rootCategoryId", "2");
		treeMap1.put("categoryName", "LearnOpus Special Category");
		treeList.add(treeMap1);
		
		Map<String,String> treeMap2 = new HashMap<>();
		treeMap2.put("categoryId", "3");
		treeMap2.put("parentCactegoryId", "1");
		treeMap2.put("rootCategoryId", "1");
		treeMap2.put("categoryName", "Mechanical");
		treeList.add(treeMap2);
		
		Map<String,String> treeMap3 = new HashMap<>();
		treeMap3.put("categoryId", "4");
		treeMap3.put("parentCactegoryId", "1");
		treeMap3.put("rootCategoryId", "1");
		treeMap3.put("categoryName", "Civil");
		treeList.add(treeMap3);
		
		Map<String,String> treeMap4 = new HashMap<>();
		treeMap4.put("categoryId", "5");
		treeMap4.put("parentCactegoryId", "1");
		treeMap4.put("rootCategoryId", "1");
		treeMap4.put("categoryName", "EEE");
		treeList.add(treeMap4);
		
		Map<String,String> treeMap5 = new HashMap<>();
		treeMap5.put("categoryId", "6");
		treeMap5.put("parentCactegoryId", "1");
		treeMap5.put("rootCategoryId", "1");
		treeMap5.put("categoryName", "ECE");
		treeList.add(treeMap5);
		
		Map<String,String> treeMap6 = new HashMap<>();
		treeMap6.put("categoryId", "7");
		treeMap6.put("parentCactegoryId", "1");
		treeMap6.put("rootCategoryId", "1");
		treeMap6.put("categoryName", "CSE");
		treeList.add(treeMap6);
		
		Map<String,String> treeMap7 = new HashMap<>();
		treeMap7.put("categoryId", "8");
		treeMap7.put("parentCactegoryId", "1");
		treeMap7.put("rootCategoryId", "1");
		treeMap7.put("categoryName", "IT");
		treeList.add(treeMap7);
		
		Map<String,String> treeMap8 = new HashMap<>();
		treeMap8.put("categoryId", "9");
		treeMap8.put("parentCactegoryId", "1");
		treeMap8.put("rootCategoryId", "1");
		treeMap8.put("categoryName", "Aeronautical");
		treeList.add(treeMap8);
		
		Map<String,String> treeMap9 = new HashMap<>();
		treeMap9.put("categoryId", "10");
		treeMap9.put("parentCactegoryId", "1");
		treeMap9.put("rootCategoryId", "1");
		treeMap9.put("categoryName", "Environmental");
		treeList.add(treeMap9);
		
		Map<String,String> treeMap10 = new HashMap<>();
		treeMap10.put("categoryId", "11");
		treeMap10.put("parentCactegoryId", "1");
		treeMap10.put("rootCategoryId", "1");
		treeMap10.put("categoryName", "Geo Informatics");
		treeList.add(treeMap10);
		
		Map<String,String> treeMap11 = new HashMap<>();
		treeMap11.put("categoryId", "12");
		treeMap11.put("parentCactegoryId", "1");
		treeMap11.put("rootCategoryId", "1");
		treeMap11.put("categoryName", "Agriculture");
		treeList.add(treeMap11);
		
		Map<String,String> treeMap12 = new HashMap<>();
		treeMap12.put("categoryId", "13");
		treeMap12.put("parentCactegoryId", "1");
		treeMap12.put("rootCategoryId", "1");
		treeMap12.put("categoryName", "Automobile");
		treeList.add(treeMap12);
		
		Map<String,String> treeMap13 = new HashMap<>();
		treeMap13.put("categoryId", "14");
		treeMap13.put("parentCactegoryId", "1");
		treeMap13.put("rootCategoryId", "1");
		treeMap13.put("categoryName", "Production");
		treeList.add(treeMap13);
		
		Map<String,String> treeMap14 = new HashMap<>();
		treeMap14.put("categoryId", "15");
		treeMap14.put("parentCactegoryId", "1");
		treeMap14.put("rootCategoryId", "1");
		treeMap14.put("categoryName", "Material Science and Manufacturing");
		treeList.add(treeMap14);
		
		Map<String,String> treeMap15 = new HashMap<>();
		treeMap15.put("categoryId", "16");
		treeMap15.put("parentCactegoryId", "1");
		treeMap15.put("rootCategoryId", "1");
		treeMap15.put("categoryName", "Industrial and Management");
		treeList.add(treeMap15);
		
		Map<String,String> treeMap16 = new HashMap<>();
		treeMap16.put("categoryId", "17");
		treeMap16.put("parentCactegoryId", "1");
		treeMap16.put("rootCategoryId", "1");
		treeMap16.put("categoryName", "Mechatronics");
		treeList.add(treeMap16);
		
		Map<String,String> treeMap17 = new HashMap<>();
		treeMap17.put("categoryId", "18");
		treeMap17.put("parentCactegoryId", "1");
		treeMap17.put("rootCategoryId", "1");
		treeMap17.put("categoryName", "Mechanical and Automation");
		treeList.add(treeMap17);
		
		Map<String,String> treeMap18 = new HashMap<>();
		treeMap18.put("categoryId", "19");
		treeMap18.put("parentCactegoryId", "1");
		treeMap18.put("rootCategoryId", "1");
		treeMap18.put("categoryName", "Industrial");
		treeList.add(treeMap18);
		
		Map<String,String> treeMap19 = new HashMap<>();
		treeMap19.put("categoryId", "20");
		treeMap19.put("parentCactegoryId", "1");
		treeMap19.put("rootCategoryId", "1");
		treeMap19.put("categoryName", "Robotics and Automation");
		treeList.add(treeMap19);
		
		Map<String,String> treeMap20 = new HashMap<>();
		treeMap20.put("categoryId", "21");
		treeMap20.put("parentCactegoryId", "1");
		treeMap20.put("rootCategoryId", "1");
		treeMap20.put("categoryName", "Electronics and Instrumentation");
		treeList.add(treeMap20);
		
		Map<String,String> treeMap21 = new HashMap<>();
		treeMap21.put("categoryId", "22");
		treeMap21.put("parentCactegoryId", "1");
		treeMap21.put("rootCategoryId", "1");
		treeMap21.put("categoryName", "Instrumentation and Control");
		treeList.add(treeMap21);
		
		Map<String,String> treeMap22 = new HashMap<>();
		treeMap22.put("categoryId", "23");
		treeMap22.put("parentCactegoryId", "1");
		treeMap22.put("rootCategoryId", "1");
		treeMap22.put("categoryName", "Biomedical");
		treeList.add(treeMap22);
		
		Map<String,String> treeMap23 = new HashMap<>();
		treeMap23.put("categoryId", "24");
		treeMap23.put("parentCactegoryId", "1");
		treeMap23.put("rootCategoryId", "1");
		treeMap23.put("categoryName", "Medical Electronics");
		treeList.add(treeMap23);
		
		Map<String,String> treeMap24 = new HashMap<>();
		treeMap24.put("categoryId", "25");
		treeMap24.put("parentCactegoryId", "1");
		treeMap24.put("rootCategoryId", "1");
		treeMap24.put("categoryName", "Chemical");
		treeList.add(treeMap24);
		
		Map<String,String> treeMap25 = new HashMap<>();
		treeMap25.put("categoryId", "26");
		treeMap25.put("parentCactegoryId", "1");
		treeMap25.put("rootCategoryId", "1");
		treeMap25.put("categoryName", "Biotechnology");
		treeList.add(treeMap25);
		
		Map<String,String> treeMap26 = new HashMap<>();
		treeMap26.put("categoryId", "27");
		treeMap26.put("parentCactegoryId", "1");
		treeMap26.put("rootCategoryId", "1");
		treeMap26.put("categoryName", "Biotechnology");
		treeList.add(treeMap26);
		
		Map<String,String> treeMap27 = new HashMap<>();
		treeMap27.put("categoryId", "28");
		treeMap27.put("parentCactegoryId", "1");
		treeMap27.put("rootCategoryId", "1");
		treeMap27.put("categoryName", "Polymer Technology");
		treeList.add(treeMap27);
		
		Map<String,String> treeMap28 = new HashMap<>();
		treeMap28.put("categoryId", "29");
		treeMap28.put("parentCactegoryId", "1");
		treeMap28.put("rootCategoryId", "1");
		treeMap28.put("categoryName", "Plastic Technology");
		treeList.add(treeMap28);
		
		Map<String,String> treeMap29 = new HashMap<>();
		treeMap29.put("categoryId", "30");
		treeMap29.put("parentCactegoryId", "1");
		treeMap29.put("rootCategoryId", "1");
		treeMap29.put("categoryName", "Textile Technology");
		treeList.add(treeMap29);
		
		Map<String,String> treeMap30 = new HashMap<>();
		treeMap30.put("categoryId", "31");
		treeMap30.put("parentCactegoryId", "1");
		treeMap30.put("rootCategoryId", "1");
		treeMap30.put("categoryName", "Fashion Technology");
		treeList.add(treeMap30);
		
		Map<String,String> treeMap31 = new HashMap<>();
		treeMap31.put("categoryId", "32");
		treeMap31.put("parentCactegoryId", "1");
		treeMap31.put("rootCategoryId", "1");
		treeMap31.put("categoryName", "Petroleum");
		treeList.add(treeMap31);
		
		Map<String,String> treeMap32 = new HashMap<>();
		treeMap32.put("categoryId", "33");
		treeMap32.put("parentCactegoryId", "1");
		treeMap32.put("rootCategoryId", "1");
		treeMap32.put("categoryName", "Textile Chemistry");
		treeList.add(treeMap32);
		
		Map<String,String> treeMap33 = new HashMap<>();
		treeMap33.put("categoryId", "34");
		treeMap33.put("parentCactegoryId", "1");
		treeMap33.put("rootCategoryId", "1");
		treeMap33.put("categoryName", "Petroleum");
		treeList.add(treeMap33);
		
		Map<String,String> treeMap34 = new HashMap<>();
		treeMap34.put("categoryId", "35");
		treeMap34.put("parentCactegoryId", "1");
		treeMap34.put("rootCategoryId", "1");
		treeMap34.put("categoryName", "Petrochemical Technology");
		treeList.add(treeMap34);
		
		Map<String,String> treeMap35 = new HashMap<>();
		treeMap35.put("categoryId", "36");
		treeMap35.put("parentCactegoryId", "1");
		treeMap35.put("rootCategoryId", "1");
		treeMap35.put("categoryName", "Food Technology");
		
		Map<String,String> treeMap36 = new HashMap<>();
		treeMap36.put("categoryId", "37");
		treeMap36.put("parentCactegoryId", "1");
		treeMap36.put("rootCategoryId", "1");
		treeMap36.put("categoryName", "Handllom and Textile Technology");
		
		Map<String,String> treeMap37 = new HashMap<>();
		treeMap37.put("categoryId", "38");
		treeMap37.put("parentCactegoryId", "1");
		treeMap37.put("rootCategoryId", "1");
		treeMap37.put("categoryName", "Marine");
		 

		Map<String, Map<String, List<Map<String, String>>>> treeStructureMap = 
				treeList.stream().collect(Collectors.groupingBy(treeTrace -> treeTrace.get("rootCategoryId"), Collectors.groupingBy(treeTrace1 -> treeTrace1.get("parentCactegoryId"))));
		System.out.println(treeStructureMap);
	}
}