package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class www {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String line ;
		List<String> rules = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader("E:/eee.txt"));
		try {
			//line = br.readLine();
			while ((line = br.readLine()) != null) {
				if(!line.isEmpty()) {
					rules.add(line);
				}
			}
			
//			rulerLib.initRule(rules);
			System.out.println("-----------------Add rule count:" + rules.size());
			System.out.println(null != null);
		} finally {
			br.close();
		}
	}
	
//	public void initRule(List<String> rules) {
//		for(String r : rules) {
//			String[] t = r.split(STRUTS_SPLIT);
//			if(t.length >= 4) {
//				//taobao.com,&q=,UTF-8,taobao
//				//tmall.com,&q=,GBK,tmall
//				//360buy.com,&body=;keyword;newVersion,UTF-8,jd
//				//jd.com,&keyword=|key=|?keyword=,UTF-8,jd
//				//gome.com,&question=|?question=,UTF-8,gome
//				//yhd.com,&tp=;.;.,UTF-8,yhd
//				//baidu.com,&wd=,UTF-8,baidu
//				hosts.put(t[0], t[3]);
//				lib.put(t[0], new MatchAndDecodeURL(t[1], t[3], t[2]));
//			}
//		}
//	}

}
