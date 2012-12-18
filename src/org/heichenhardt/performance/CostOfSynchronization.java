package org.heichenhardt.performance;

/**
 * Compare the cost of synchronisation of getter and setters in java beans.
 * @author Henrik
 *
 */
public class CostOfSynchronization {

	public static void main(String[] args) throws Exception {
		long synchMinTime = runTest(new SynchronizedData());
		long minTime = runTest(new Data());
		
		System.out.println(synchMinTime);
		System.out.println(minTime);
	}

	/**
	 * Run the test.
	 * @param data	Pass in the bean.
	 * @return	The minimum runtime of this test.
	 */
	private static long runTest(final Data data) {
		long minTime = Long.MAX_VALUE;
		//Run test 10 times.
		for(int i=0; i<10; i++) {
			final long start = System.currentTimeMillis();
			for(long index=0; index < 2000000000L; index++) {
				data.setId(data.getId() + index);
			}
			final long end = System.currentTimeMillis();
			//Remember minimal time to run the test.
			minTime = Math.min(minTime, end - start);
		}
		return minTime;
	}
	/**
	 * Synchronised bean.
	 * @author Henrik
	 *
	 */
	private static class SynchronizedData extends Data {
		@Override
		public synchronized long getId() {
			return super.id;
		}
		@Override
		public synchronized void setId(long id) {
			super.id = id;
		}
	}
	
	/**
	 * Normal bean.
	 * @author Henrik
	 *
	 */
	private static class Data {
		private long id = 0L;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
	}
}
