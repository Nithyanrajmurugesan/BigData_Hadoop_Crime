package saferchicago;


import java.io.IOException;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import mrtools.CountMap;
import mrtools.NBest;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;



public class SafeReducer extends MapReduceBase
implements Reducer<Text, Text, Text, Text> {
	
	
	    NBest<Text> Best = new NBest<Text>(3);
        CountMap<Text> Counts= new CountMap<Text>();
        public void reduce(Text key, Iterator<Text> values,
                        OutputCollector<Text, Text> output,
                        Reporter reporter) throws IOException {
        	
        		
                Counts.clear();
                Counts.countInto(values);
                Best.clear();
                Best.putMap(Counts);
                output.collect(key,new Text(Best.bestEntryCountTSV(",")));
                
            
        }
}

