interface partionedArrInfo {
  arr: number[];
  pivot_inde: number;
}

function quickSort(arr: number[]): number[] {
  if (arr.length <= 1) {
    return arr;
  }
  const info = partition(arr);
  const left_part = info.arr.slice(0, info.pivot_inde);
  const right_part = info.arr.slice(info.pivot_inde + 1);

  return quickSort(left_part).concat(
    info.arr[info.pivot_inde],
    quickSort(right_part)
  );
}

function partition(arr: number[]): partionedArrInfo {
  const newArr = new Array(arr.length);
  const pivot = arr[0];
  let left = 0;
  let right = arr.length - 1;
  for (let i = 0; i < arr.length; i++) {
    if (arr[i] < pivot) {
      newArr[left] = arr[i];
      left++;
    } else if (arr[i] > pivot) {
      newArr[right] = arr[i];
      right--;
    }
  }
  for (let j = left; j < right + 1; j++) {
    newArr[j] = pivot;
  }
  return {
    arr: newArr,
    pivot_inde: left,
  };
}

// Tony Hoare's partitioning
function partition2(arr: number[]): partionedArrInfo {
  const pivot = arr[0];
  let left = 1;
  let right = arr.length - 1;
  while (left < right) {
    if (arr[left] >= pivot && arr[right] <= pivot) {
      swap(arr, left, right);
      left++;
      right--;
    }
    if (arr[left] < pivot) {
      left++;
    }
    if (arr[right] > pivot) {
      right--;
    }
  }
  swap(arr, 0, right);
  return {
    arr,
    pivot_inde: right,
  };
}

function swap(arr: number[], index1: number, index2: number) {
  let value1 = arr[index1];
  arr[index1] = arr[index2];
  arr[index2] = value1;
}

let arr = [100, 16, 19, 120, 20, 340, 100];
const result = quickSort(arr);
console.log(result);
