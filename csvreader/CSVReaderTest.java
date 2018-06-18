

public class CSVReaderTest
{
   
    

    public void test() throws Exception
    {
        CSVReader reader=new CSVReader("file.csv",",");
        String[] data= reader.getNextData();
        while(null!=data)
        {
            for(String text:data)
            {
                System.out.print(text);
            }
            data= reader.getNextData();
            System.out.println();
        }
        
    }
}
