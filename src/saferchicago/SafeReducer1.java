package saferchicago;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.Reducer;

public  class SafeReducer1 extends MapReduceBase
  implements Reducer<Text, IntWritable, Text, Text> {
	


  public void reduce(Text key, Iterator<IntWritable> values,
                     OutputCollector<Text, Text> output,
                     Reporter reporter) throws IOException {
	  
    int sum = 0;
    while (values.hasNext()) {
      sum += values.next().get();
    }
    

    
   
    
    
    output.collect(key, new Text("\t" + sum));
    
    
    
   
  }
}