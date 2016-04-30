package saferchicago;

import java.util.Scanner;
import java.io.IOException;

//import latlongdist.CatDiv;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class SafeMain{

  public static void main(String[] args) throws IOException {
	  
	  
	  
	  Configuration conf1 = new Configuration();
	 
	  FileSystem fs = FileSystem.get(conf1);
	 
	  if(fs.exists(new Path(args[1]))){
	     
	     fs.delete(new Path(args[1]),true);
	  }
	  
	  JobConf conf = new JobConf(SafeMain.class);
	 // for mapper and reducer
	    conf.setOutputKeyClass(Text.class);
	    conf.setOutputValueClass(Text.class);
	    conf.setMapOutputKeyClass(Text.class);
	    conf.setMapOutputValueClass(Text.class);
	    conf.setMapperClass(SafeMapper.class);
	    conf.setReducerClass(SafeReducer.class);
	    
	  // for mapper1 and reducer1
	   /*
	    conf.setOutputKeyClass(Text.class);
	    conf.setOutputValueClass(IntWritable.class);
	    conf.setMapOutputKeyClass(Text.class);
	    conf.setMapOutputValueClass(IntWritable.class);
	    conf.setMapperClass(SafeMapper1.class);
	    conf.setReducerClass(SafeReducer1.class);
	    */
	  
	  
	   

	    FileInputFormat.addInputPath(conf, new Path(args[0]));
	    FileOutputFormat.setOutputPath(conf, new Path(args[1]));
	    JobClient.runJob(conf);
	    
	  
	  }
}
