import { ComponentsLayout } from './ComponentsLayout';

export function AddEmployee() {
	return (
		<ComponentsLayout>
			<div className='py-12'>
				<div className='max-w-4xl mx-auto bg-white shadow overflow-hidden sm:rounded-lg'>
					<div className='px-4 py-5 sm:px-6'>
						<h3 className='text-lg leading-6 font-medium text-gray-900'>
							Add Employee
						</h3>
					</div>
					<div className='border-t border-gray-200 px-4 py-5 sm:p-0'>
						<dl className='sm:divide-y sm:divide-gray-200'>
							<div className='py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6'>
								<dt className='text-sm font-medium text-gray-500'>Full name</dt>
								<dd className='mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2'>
									<input
										id='fullname'
										name='fullname'
										type='text'
										autoComplete='off'
										required
										className='appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm'
									/>
								</dd>
							</div>
							<div className='py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6'>
								<dt className='text-sm font-medium text-gray-500'>Email</dt>
								<dd className='mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2'>
									<input
										id='email'
										name='email'
										type='email'
										autoComplete='email'
										required
										className='appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm'
									/>
								</dd>
							</div>
							<div className='py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6'>
								<dt className='text-sm font-medium text-gray-500'>Password</dt>
								<dd className='mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2'>
									<input
										id='password'
										name='password'
										type='password'
										autoComplete='current-password'
										required
										className='appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm'
									/>
								</dd>
							</div>
							<div className='py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6'>
								<dt className='text-sm font-medium text-gray-500'>
									Confirm Password
								</dt>
								<dd className='mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2'>
									<input
										id='password'
										name='password'
										type='password'
										autoComplete='current-password'
										required
										className='appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm'
									/>
								</dd>
							</div>
							<div className='py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-6'>
								<dt className='text-sm font-medium text-gray-500'></dt>
								<dd className='mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2'>
									<div className='flex justify-end items-center space-x-4'>
										<button
											type='button'
											className='relative inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md text-white bg-blue-600 shadow-sm hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500'>
											<span>Add Employee</span>
										</button>
									</div>
								</dd>
							</div>
						</dl>
					</div>
				</div>
			</div>
		</ComponentsLayout>
	);
}
