package Util;

import java.io.*;

public class CacheManager {

    private String cacheFilePath;

   public CacheManager(String file)
   {
      cacheFilePath = file;
   }
   public CacheManager(){}

   public void saveToCache(Object cartConfig)
   {
      try(FileOutputStream fileOut = new FileOutputStream(cacheFilePath);
          ObjectOutputStream out = new ObjectOutputStream(fileOut)){
         out.writeObject(cartConfig); //Serializing the object as bytes and writing in the file
         System.out.println("Saved in Cache Successfully");
      }
      catch (IOException e)
      {
          e.printStackTrace();
      }
   }

   public Object loadObjectFromCache()
   {
       Object obj = null;

       File file = new File(cacheFilePath);
       if(file.exists())
       {
           try(FileInputStream fileIn = new FileInputStream(cacheFilePath);
             ObjectInputStream in = new ObjectInputStream(fileIn))
           {
               obj = in.readObject();
               System.out.println("Cache Loaded Successfully");
           }
           catch (IOException | ClassNotFoundException e)
           {
               e.printStackTrace();
           }
       }
       else {
           System.out.println("Cache File Not Found");
       }
       return obj;
   }

   public void clearCache()
   {
       File cacheFile = new File(cacheFilePath);
       if(cacheFile.exists())
       {
           cacheFile.delete();
           System.out.println("Cache File Deleted");
       }
       else
       {
           System.out.println("Cache File Not Found");
       }
   }

}
