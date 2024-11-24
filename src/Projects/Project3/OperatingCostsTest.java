package Projects.Project3;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class OperatingCostsTest {
	
	private final double[][] givenCosts = {
		{0.09,4.2,0.22,6.1,30,6.2},
		{0.08,7.6,0.53,9.5,48.5,8.88},
		{0.19,0.7,0.35,6.1,47,8.6},
		{0.23,2.0,0.88,8.71,28,7.49},
		{0.10,6.6,0.83,10.8,43.6,7.46},
		{0.22,2.3,0.28,8.8,28,14.29}
	};
	
	private final String[] givenMetrics = {"failure-rate","hardware","maintenance","power","area","climate-control"};
	
	private final double[][] unknownCosts = {
		{0.08,8.03,0.03,8.78,58.24,11.61},
		{0.14,7.25,0.43,10.26,51.84,7.23},
		{0.13,1.57,0.62,7.08,50.76,10.16},
		{0.23,4.51,0.83,7.14,51.38,12.46},
		{0.14,5.37,0.70,6.81,43.02,11.47},
		{0.05,5.75,0.53,7.95,56.59,9.17},
		{0.25,4.53,0.57,9.46,26.49,9.39},
		{0.11,6.76,0.25,10.20,26.70,7.25},
		{0.10,5.29,0.68,9.78,29.35,6.49},
		{0.08,5.35,0.17,10.36,23.45,15.61},
		{0.09,7.38,0.78,5.72,52.17,6.92,},
		{0.17,6.39,0.02,9.15,38.51,11.78}
	};
	
	
	private final String[] unknownMetrics = {"failure-rate","hardware","maintenance","power","area","climate-control","running-cost","tax-liability","labor-cost","security-cost","environmental-cost"};
	
	
	@Test
	public void getMetricsGivenDataTest(){
		String[] expected = givenMetrics;
		Assert.assertArrayEquals( expected, OperatingCosts.getOperatingMetrics("failure-rate,hardware,maintenance,power,area,climate-control"));
	}
	
	@Test
	public void getMetricsUnknownDataTest(){
		String[] expected = unknownMetrics;
		Assert.assertArrayEquals( expected, OperatingCosts.getOperatingMetrics("failure-rate,hardware,maintenance,power,area,climate-control,running-cost,tax-liability,labor-cost,security-cost,environmental-cost"));
	}
	
	
	@Test
	public void getgetDataCentersGivenDataTest(){
		double[][] expected = givenCosts;
		Assert.assertArrayEquals( expected, OperatingCosts.getDataCenters("0.09,4.2,0.22,6.1,30,6.2<>0.08,7.6,0.53,9.5,48.5,8.88<>0.19,0.7,0.35,6.1,47,8.6<>0.23,2.0,0.88,8.71,28,7.49<>0.10,6.6,0.83,10.8,43.6,7.46<>0.22,2.3,0.28,8.8,28,14.29"));
	}
	
	@Test
	public void getDataCentersUnknownDataTest(){
		double[][] expected = unknownCosts;
		Assert.assertArrayEquals( expected, OperatingCosts.getDataCenters("0.08,8.03,0.03,8.78,58.24,11.61<>0.14,7.25,0.43,10.26,51.84,7.23<>0.13,1.57,0.62,7.08,50.76,10.16<>0.23,4.51,0.83,7.14,51.38,12.46<>0.14,5.37,0.70,6.81,43.02,11.47<>0.05,5.75,0.53,7.95,56.59,9.17<>0.25,4.53,0.57,9.46,26.49,9.39<>0.11,6.76,0.25,10.20,26.70,7.25<>0.10,5.29,0.68,9.78,29.35,6.49<>0.08,5.35,0.17,10.36,23.45,15.61<>0.09,7.38,0.78,5.72,52.17,6.92,<>0.17,6.39,0.02,9.15,38.51,11.78"));
	}
	
	
	@Test
	public void searchHighestMetricGivenDataTest(){
		
		Assert.assertEquals("When searching data center #1 for it's highest metric we", "area and climate-control", OperatingCosts.searchHighestMetric(givenCosts,givenMetrics,1));
		
		Assert.assertEquals("When searching data center #3 for it's highest metric we", "area and climate-control", OperatingCosts.searchHighestMetric(givenCosts,givenMetrics,3));
		
		Assert.assertEquals("When searching data center #5 for it's highest metric we", "area and power", OperatingCosts.searchHighestMetric(givenCosts,givenMetrics,5));
	}
	
	@Test
	public void searchHighestMetricUnknownDataTest(){
		
		Assert.assertEquals("When searching data center #2 for it's highest metric we", "area and power", OperatingCosts.searchHighestMetric(unknownCosts,givenMetrics,2));
		
		Assert.assertEquals("When searching data center #6 for it's highest metric we", "area and climate-control", OperatingCosts.searchHighestMetric(unknownCosts,givenMetrics,6));
		
		Assert.assertEquals("When searching data center #8 for it's highest metric we", "area and power", OperatingCosts.searchHighestMetric(unknownCosts,givenMetrics,8));
	}
	
	@Test
	public void searchHighestDataCenterGivenDataTest(){
		
		Assert.assertEquals("When calling searchHighestDataCenter to find the data center with the highest \"hardware\" metric, we",2,OperatingCosts.searchHighestDataCenter(givenCosts,givenMetrics,"hardware"));
		
		Assert.assertEquals("When calling searchHighestDataCenter to find the data center with the highest \"maintenance\" metric, we",4,OperatingCosts.searchHighestDataCenter(givenCosts,givenMetrics,"maintenance"));
		
		Assert.assertEquals("When calling searchHighestDataCenter to find the data center with the highest \"climate-control\" metric, we",6,OperatingCosts.searchHighestDataCenter(givenCosts,givenMetrics,"climate-control"));
	}
	
	@Test
	public void searchHighestDataCenterUnknownDataTest(){
		
		Assert.assertEquals("When calling searchHighestDataCenter to find the data center with the highest \"hardware\" metric, we",1,OperatingCosts.searchHighestDataCenter(unknownCosts,givenMetrics,"hardware"));
		
		Assert.assertEquals("When calling searchHighestDataCenter to find the data center with the highest \"maintenance\" metric, we",4,OperatingCosts.searchHighestDataCenter(unknownCosts,givenMetrics,"maintenance"));
		
		Assert.assertEquals("When calling searchHighestDataCenter to find the data center with the highest \"power\" metric, we",10,OperatingCosts.searchHighestDataCenter(unknownCosts,givenMetrics,"power"));
	}
	
	
	@Test
	public void findDataCenterViabilityGivenDataTest(){
		
		Assert.assertArrayEquals("When checking the result of findDataCenterViability for the given productivity data, we",new int[] {1,3,4,6},OperatingCosts.findAnnualOperationCost(givenCosts));
		
	}
	
	@Test
	public void findDataCenterViabilityUnknownDataTest(){
		
		Assert.assertArrayEquals("When checking the result of findDataCenterViability for the unknown productivity data, we",new int[] {1,3,4,5,6,8,10,12},OperatingCosts.findAnnualOperationCost(unknownCosts));
		
	}
	
	
}
