
package saferchicago;

import java.io.IOException;
import java.io.StringReader;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import com.opencsv.CSVReader;

public class SafeMapper1 extends MapReduceBase
implements Mapper<LongWritable, Text, Text, IntWritable> {
	
	private final static IntWritable one = new IntWritable(1);
	
	private Text type = new Text();
	private Text CommunityArea = new Text();
	private Text year = new Text();
	
	public void map(LongWritable key, Text value,
            OutputCollector<Text, IntWritable> output,
            Reporter reporter) throws IOException {
		
		String line = value.toString();
		  CSVReader R = new CSVReader(new StringReader(line));
		  String[] ParsedLine = R.readNext();
		  R.close();
		  
		
		  CommunityArea.set(ParsedLine[13].trim());
		  type.set(ParsedLine[5].trim());
		  year.set(ParsedLine[17].trim());
		  
		  if (type.toString().equals("DECEPTIVE PRACTICE")){
			  output.collect(CommunityArea, one);
			  
		  }
		  /*
          output.collect(CommunityArea, one);
		  */
		  
		  
	}
}
