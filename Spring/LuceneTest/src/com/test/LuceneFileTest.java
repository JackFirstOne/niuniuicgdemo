package com.test;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.packed.DirectReader;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class LuceneFileTest {

       @Test
       public void createIndex() throws IOException {
              //创建Dir，指定索引保存位置 ,当文件夹不存在系统会主动创建一个文件夹
              File file = new File("d://lucene");
              Directory directory = FSDirectory.open(file.toPath());

              //基于Dir对象创建索引写入对象
              IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new IKAnalyzer());
              IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);

              //读取磁盘文件，对应每个文件创建文档对象
              File dir = new File("d://searchsource");
              File [] files = dir.listFiles();
              System.out.println(files.toString());

              //循环获取文件并为每个文件创建对象
              for (File file1:files) {
                     //获取文件名称 :先分词后索引 TextField
                     String fileName = file1.getName();
                     System.out.println(fileName);

                     //获取文件的路径 ：不分词不索引 StoredField
                     String filePath = file1.getPath();
                     System.out.println(filePath);

                     //获取文件内容 先分词后索引  TextField
                     String fileContent = FileUtils.readFileToString(file1, "UTF-8");
                     System.out.println(fileContent);

                     //获取文件的大小 不分词，不索引 LongField
                     long fileSize = FileUtils.sizeOf(file1);
                     System.out.println(fileSize);

                     //创建lucene 的域对象 Field
                     //TextField和StringField都是对字符串进行索引，不同是TextField既索引又分词，StringFiled只索引不分词（作为一个整体索引）  Store是否存储
                     Field fieldName = new TextField("fileName", fileName, Field.Store.YES);
                     //构建不同类型的Field, 不分词, 不索引, 要存储.(如: 商品图片路径)
                     StoredField fieldPath = new StoredField("filePath", filePath);
                     Field fieldContent = new TextField("fileContent", fileContent, Field.Store.YES);
                     Field fieldSize = new LongPoint("fileSize", fileSize);
                     Field fieldSizes = new StoredField("fileSize", fileSize);

                     //创建文档对象
                     Document document = new Document();
                     //将域存入文档对象中
                     document.add(fieldName);
                     document.add(fieldPath);
                     document.add(fieldContent);
                     document.add(fieldSize);
                     document.add(fieldSizes);

                     //将文档写入到Dir
                     indexWriter.addDocument(document);
              }
                 indexWriter.close();
       }

       @Test
       public void createIndexTwo() throws IOException {

              //创建索引存储位置：dir
              Directory dir = FSDirectory.open(new File("d://lucene2").toPath());

              //创建索引配置
              IndexWriterConfig indexWriterConfig = new IndexWriterConfig(new IKAnalyzer());
              IndexWriter indexWriter = new IndexWriter(dir,indexWriterConfig);

              File indexDir = new File("d://lucene2");
              File[] files1 = indexDir.listFiles();
              if (files1.length>0){
                   indexWriter.deleteAll();
              }
              //读取系统文件
              //获取文件夹
              File fileDir = new File("d://searchsource");
              //获取文件夹中的文件
              File[] files = fileDir.listFiles();
              //循环文件创建文件域及文档
              for (File file:files) {
                     //创建文档
                     Document document = new Document();
                     //获取文件名称
                     String fileName = file.getName();
                     //文件名称为String类型，即索引又分词 用TextField ,如果只索引不分词用StringField
                     Field fieldName = new TextField("fileName",fileName,Field.Store.YES);
                     //将域添加到文档中
                     document.add(fieldName);

                     //获取文件的路径
                     String filePath = file.getPath();
                     //文件路径不分词，不索引，用StoredField
                     Field fieldPath = new StoredField("filePath", filePath);
                     //将域添加到文档中
                     document.add(fieldPath);

                     //获取文档的内容
                     String fileContent = FileUtils.readFileToString(file, "UTF-8");
                     //内容既分词又索引，用TextField
                     Field fieldConent = new TextField("fileConent", fileContent, Field.Store.YES);
                     //将内容域对象添加到文档中
                     document.add(fieldConent);

                     //获取文档大小
                     long fileSize = FileUtils.sizeOf(file);
                     LongPoint fieldSize = new LongPoint("fileSize", fileSize);
                     //不分词不索引
                     StoredField fieldSizes = new StoredField("fileSize", fileSize);
                     document.add(fieldSizes);

                     //将文档写入索引
                     indexWriter.addDocument(document);
              }
              //8 提交
              indexWriter.commit();
              indexWriter.close();
       }

       /**
        * 查询索引
        * 使用特定分词器搜索
        * QueryParser parser =new QueryParser("contents", new StandardAnalyzer());
        * query =parser.parse("11 a and hello");
        *
        * 按词条搜索—TermQuery
        *
        * 按“与或”搜索—BooleanQuery
        * 1.和： MUST与MUST_NOT
        *
        * 2.或： SHOULD与SHOULD
        *
        * 3.A与B的并集－B MUST与MUST_NOT
        *
        * 在某一范围内搜索—RangeQuery：一般用于时间范围
        *
        * 使用前缀搜索—PrefixQuery
        *
        * 短语搜索—PhraseQuery
        */
       @Test
       public void search() throws IOException {
               //指定索引存储位置
              Directory fsDirectory = FSDirectory.open(new File("d://lucene2").toPath());
              //创建文件读取对象
              IndexReader indexReader = DirectoryReader.open(fsDirectory);
              //创建索引查询对象
              IndexSearcher indexSearcher = new IndexSearcher(indexReader);
              //创建查询对象   按词条搜索—TermQuery   Term：关键词
              Query query = new TermQuery(new Term("fileName","lucene"));
              //执行查询操作，得到一个TopDocs对象 ,参数为查询对象及查询的条数
              TopDocs topDocs = indexSearcher.search(query, 100);
              //获取查询总记录数
              long totalHits = topDocs.totalHits;

              // System.out.println("共获取"+totalHits+"条数据");
              //获取文档对象
              ScoreDoc[] scoreDocs = topDocs.scoreDocs;
              //System.out.println(scoreDocs.length+"文档数量");
              for (ScoreDoc scoreDoc:scoreDocs) {
                     //获取文档Id
                     int docId = scoreDoc.doc;
                     //根据Id获取文档
                     Document doc = indexSearcher.doc(docId);
                     System.out.println(doc.get("fileName")+","+doc.get("fileSize"));
                     //System.out.println(doc.get("fileContent"));

              }

       }

       @Test
       public void searchTwo() throws IOException {
              //获取索引存放文件夹
              Directory directory = FSDirectory.open(new File("d://lucene2").toPath());
              //创建索引读取对象
              IndexReader reader = DirectoryReader.open(directory);
              //创建索引查询对象
              IndexSearcher indexSearcher = new IndexSearcher(reader);
              //创建查询对象
              Query query = new TermQuery(new Term("fileName", "spring"));
              //通过索引查询对象查询
              TopDocs topDocs = indexSearcher.search(query, 100);
              ScoreDoc[] scoreDocs = topDocs.scoreDocs;
              long totalHits = topDocs.totalHits;
              for (ScoreDoc scoreDoc:scoreDocs) {
                     int docId = scoreDoc.doc;
                     Document doc = indexSearcher.doc(docId);
                     System.out.println(doc.get("fileName"));
              }
       }
}
