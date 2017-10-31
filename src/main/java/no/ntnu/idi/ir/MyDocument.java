package no.ntnu.idi.ir;


import java.io.File;

import org.apache.lucene.document.*;

/*
 * The task is to update the given `MyDocument' class (i.e implement the 'Document(File F)' method) to index the following fields per document:
 *
 * - path, the path of the file
 * - from, whatever is stored in the from field of the given message
 * - subject, the subject of the e-mail
 * - contents, the actual e-mail contents
 *
 * All `from', `subject', and `contents' should be searchable, i.e. store their re-spective term vectors.
 * Look at the given `NewsDocument' class that reads a text file and has better methods for `from', `subject', and `contents'.
 *
 * =============
 *
 * Lucene official documentation:
 * http://lucene.apache.org/core/4_10_1/index.html
 *
 * Useful resources:
 *   http://oak.cs.ucla.edu/cs144/projects/lucene/
 *   http://lingpipe-blog.com/2014/03/08/lucene-4-essentials-for-text-search-and-indexing/
 *
 */


public class MyDocument{

	public static Document Document (File f) throws java.io.FileNotFoundException{

		// make a new, empty document
		Document doc = new Document();

		// use the news document wrapper
		NewsDocument newsDocument = new NewsDocument(f);

		//TODO: create structured lucene document

		doc.add(new Field("id", f.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED, Field.TermVector.NO));
		doc.add(new Field("from", newsDocument.getFrom(), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
		doc.add(new Field("subject", newsDocument.getSubject(), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
		doc.add(new Field("contents", newsDocument.getContent(), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));

		return doc;
	}

}
