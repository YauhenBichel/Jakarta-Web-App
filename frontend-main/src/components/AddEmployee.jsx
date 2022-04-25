import { ComponentsLayout } from './ComponentsLayout';
import { useForm } from "react-hook-form";
import axios from 'axios';

export function AddEmployee() {
	const { register, handleSubmit, watch, formState: { errors } } = useForm();
  const onSubmit = (data) => {
		axios.post('http://localhost:8080/holidaysystem/api/employee', {
			firstName: data.firstName,
			lastName: data.lastName,
			department: data.department,
			role: data.role,
			years: data.years,
		})
		.then((response) => {
    	console.log(response);
  	})
  	.catch((error) => {
    	console.log(error);
  	});
		console.log(data);
	};

	return (
		<ComponentsLayout>
			<div className='py-12'>
				<div className='max-w-4xl mx-auto bg-white shadow overflow-hidden sm:rounded-lg'>
					<div className='px-4 py-5 sm:px-6'>
						<h3 className='text-lg leading-6 font-medium text-gray-900'>
							Add Employee
						</h3>
					</div>
					<form onSubmit={handleSubmit(onSubmit)}>
						<div className='border-t border-gray-200 px-4 py-5 sm:p-0'>
							<dl className='sm:divide-y sm:divide-gray-200'>
								<div className='py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6'>
									<dt className='text-sm font-medium text-gray-500'>First name</dt>
									<dd className='mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2'>
										<input
											{...register('firstName', { required: true })}
											id='firstName'
											name='firstName'
											type='text'
											autoComplete='off'
											required
											className='appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm'
										/>
									</dd>
								</div>
								<div className='py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6'>
									<dt className='text-sm font-medium text-gray-500'>Last name</dt>
									<dd className='mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2'>
										<input
											{...register('lastName', { required: true })}
											id='lastName'
											name='lastName'
											type='text'
											autoComplete='off'
											required
											className='appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm'
										/>
									</dd>
								</div>
								<div className='py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6'>
									<dt className='text-sm font-medium text-gray-500'>Years</dt>
									<dd className='mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2'>
										<input
											{...register('years', { required: true })}
											id='years'
											name='years'
											type='number'
											autoComplete='off'
											required
											className='appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm'
										/>
									</dd>
								</div>
								<div className='py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6'>
									<dt className='text-sm font-medium text-gray-500'>Role</dt>
									<dd className='mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2'>
										<select className='appearance-none rounded-md w-full border-gray-300 shadow-sm px-3 py-2 placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm' {...register("role")} id="role">
											<option value="HEAD">HEAD</option>
        							<option value="DEPUTY HEAD">DEPUTY HEAD</option>
        							<option value="MANAGER">MANAGER</option>
											<option value="SENIOR MEMBER">SENIOR MEMBER</option>
											<option value="JUNIOR MEMBER">JUNIOR MEMBER</option>
											<option value="APPRENTICE">APPRENTICE</option>
										</select>
									</dd>
								</div>
								<div className='py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6'>
									<dt className='text-sm font-medium text-gray-500'>Department</dt>
									<dd className='mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2'>
										<select className='appearance-none rounded-md w-full border-gray-300 shadow-sm px-3 py-2 placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm' {...register("department")} id="department">
											<option value="ENGINEERING">ENGINEERING</option>
        							<option value="PLUMBING">PLUMBING</option>
        							<option value="ROOFING">ROOFING</option>
											<option value="CARPENTRY">CARPENTRY</option>
											<option value="BRICKLAYING">BRICKLAYING</option>
											<option value="OFFICE">OFFICE</option>
										</select>
									</dd>
								</div>

								<div className='py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6'>
									<dt className='text-sm font-medium text-gray-500'></dt>
									<dd className='mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2'>
										<div className='flex justify-end items-center space-x-4'>
											<input
												type='submit'
												className='relative inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md text-white bg-blue-600 shadow-sm hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500' />
										</div>
									</dd>
								</div>
							</dl>
						</div>
					</form>
				</div>
			</div>
		</ComponentsLayout>
	);
}
