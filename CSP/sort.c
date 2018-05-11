#include<stdio.h>

/*
helper function to print 1-dimentional array
*/
void array_printer(int *a,int size);

/*
bubble_sort():

bubbles up the largest value to the last position in the first iteration;

bubbles up the second largest value to the second last position in the second iteration and so on
*/
void bubble_sort(int *a,int size);

/*
selection_sort():

i=0;

selects the minimum element and inserts it into position i

i++;
*/
void selection_sort(int *a, int size);

/*
insertion_sort():

inserts the considered element(key) into its correct position (very similar to inserting a value into a specific position in the array);
moves up all values larger than the key by 1 space and then inserts the key into correct position
*/
void insertion_sort(int *a,int size);

/*
merge_sort(): 

merge_sort() - used to split the array into two subarrays and then sort it by merging

merge() - used to correctly merge arrays and form a sorted array

find_middle() - used to find the middle index of the array
*/
void merge(int *a, int low,int middle, int high);
void merge_sort(int *a, int low, int high);
int find_middle(int *a, int low, int high);

/*
quick_sort():

partition() - used to partition the array around a pivot element. This indicates that the pivot will be correctly positioned.
	Each element is compared with the pivot and all elements less than the pivot are placed to the left of the pivot while all elements larger than the pivot are placed after the pivot. 
	two subarrays are created in the process;Left and Right
	Left := array of elements less than or equal to pivot.
	Right := array of elements greater than pivot.

quick_sort(array,low,high) - the main recursive function.
	First calls the partition() helper function to find the pivot location.
	calls quicksort on array with low = low and high = pivot - 1
	calls quicksort on array with low = pivot + 1 and high = high
*/

void quick_sort(int *a,int low, int high);
int partition(int *a,int low, int high);

void main()
{
        int ar[5] = {20,10,30,40,5};

	printf("before sorting\n");
	array_printer(ar,5);

	printf("after bubble sort\n");
	bubble_sort(ar,5);
	array_printer(ar,5);

	int arr[] = {20,10,30,40,5};
	
	printf("after selection sort\n");
	selection_sort(arr,5);
	array_printer(arr,5);

	int arra[] = {20,10,30,40,5};

        printf("after insertion sort\n");
        insertion_sort(arra,5);
        array_printer(arra,5);	

	int array[] = {20,10,30,40,5};

	printf("after merge_sort\n");
	merge_sort(array,0,4);
	array_printer(array,5);

	int array1[] = {20,10,30,40,5};
	
	printf("after quick_sort\n");
        quick_sort(array1,0,4);
        array_printer(array1,5);
}

void array_printer(int *a,int size)
{
	for(int i =0;i<size;i++)
	{
		printf("%d ",a[i]);
	}
	printf("\n");
}

void bubble_sort(int *a, int size)
{
        int i,j,temp;
        for(i=0;i<size;i++)
        {
                for(j=0;j<size-1-i;j++)
                {
                        if(a[j]>a[j+1])
                        {
                                temp=a[j];
                                a[j]= a[j+1];
                                a[j+1] = temp;
                        }
                }
	}
}

void selection_sort(int *a, int size)
{
        int i,j,temp;
        for(i=0;i<size;i++)
        {
                for(j=i+1;j<size;j++)
                {
                        if(a[i]>a[j])
                        {
                                temp=a[i];
                                a[i]= a[j];
                                a[j] = temp;
                        }
                }
        }
}

void insertion_sort(int *a,int size)
{
	int i,j,key;
	for(i=1;i<size;i++)
	{
		j=i;
		key=a[i];
		while(j>0 && key<a[j-1])
		{
			a[j]=a[j-1];
			j--;
		}
		a[j] = key;
	}
}

int find_middle(int *a,int low,int high)
{
	return ((high-low)/2)+low;	
}

void merge_sort(int *a,int low, int high)
{
	if(low<high)
	{
		int middle = find_middle(a,low,high);
	
		//DFS traversal 

		//creates the first subarray
		merge_sort(a,low,middle);

		//creates the second subarray
		merge_sort(a,middle+1,high);
		
		//each subarray is sorted
		//merges the above subarrays in a sorted order
		merge(a,low,middle,high);
	}
}

void merge(int *a,int low, int middle, int high)
{
	int leftarray_size = (middle-low)+1;
	int rightarray_size = high - middle;

	int leftarray[leftarray_size];
	int rightarray[rightarray_size];
	
	int i=0,j,k;

	/*
	creates two temporary arrays of the subarrays;Left and Right
	*/
	while(i<leftarray_size)
	{
		leftarray[i] = a[low+i];
		i++;
	}

	i=0;
	while(i<rightarray_size)
        {
                rightarray[i] = a[middle+1+i];
		i++;
        }

	i=0;
	j=0;
	k=low;
	/*
	Stores the values of subarrays into the original array by checking
	if left[i] <= right[j]
	for example:
		left = {2,5,7} right = {3,4,6}
		left[0]<right[0] -> 2
		left[1]>right[0] -> 3
		left[1]>right[1] -> 4
		left[1]<right[2] -> 5
		left[2]>right[2] -> 6
		and remaining    -> 7
	*/
	while(i<leftarray_size && j<rightarray_size)
	{
		if(leftarray[i]<=rightarray[j])
		{
			a[k] = leftarray[i];
			i++;
		}
		else
		{
			a[k] = rightarray[j];
			j++;
		}
		k++;
	}
	while(i<leftarray_size)
	{
		a[k] = leftarray[i];
		i++;
		k++;
	}
	while(j<rightarray_size)
        {
                a[k] = rightarray[j];
                j++;
                k++;
        }
}

void quick_sort(int *a,int low, int high)
{
	if(low<high)
	{
		int pivot_position = partition(a,low,high);
		
		quick_sort(a,low,pivot_position - 1);
		quick_sort(a,pivot_position + 1, high);
	}
}

int partition(int *a,int low, int high)
{
	int temp, pivot = a[high];

	//position of last found element to be less than or equal to pivot 
	int i = -1; //-1 indicates no element found (<= pivot)

	int j;
	//move all elements <= pivot to one side
	for(j=0; j < high;j++)
	{
		if(a[j]<=pivot)
		{
			i++;
			temp = a[i];
			a[i] = a[j];
			a[j] = temp;
			 	
		}
	}
	
	//move pivot to correct position
	i++;
	temp = a[high];
	a[high] = a[i];
	a[i] = temp;

	return(i);
}
