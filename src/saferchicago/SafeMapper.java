package saferchicago;

import java.io.IOException;
import java.io.StringReader;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import com.opencsv.CSVReader;

public class SafeMapper extends MapReduceBase
implements Mapper<LongWritable, Text, Text, Text> {

private Text CommunityArea = new Text();
private Text type = new Text();
private Text iucr = new Text();
private Text year = new Text();
public void map(LongWritable key, Text value,
                OutputCollector<Text, Text> output,
                Reporter reporter) throws IOException {
//	
//	
  String line = value.toString();
  CSVReader R = new CSVReader(new StringReader(line));
  String[] ParsedLine = R.readNext();
  R.close();
  
  
  CommunityArea.set(ParsedLine[13].trim());
  type.set(ParsedLine[5].trim());
  iucr.set(ParsedLine[4].trim());
  year.set(ParsedLine[17].trim());
  // Get number of crime types and community area
  /*
  output.collect(CommunityArea,type);
  */
  // years/ Community area/ type
  if(year.toString() != "2001" & year.toString()!="2002"){
  output.collect(year, new Text(CommunityArea+","+type));
  
  }
  

 }
}
