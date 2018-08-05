/*
 * Copyright (c) 2012-2018, Peter Abeles. All Rights Reserved.
 *
 * This file is part of DDogleg (http://ddogleg.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ddogleg.optimization.lm;

import org.ddogleg.optimization.UnconstrainedLeastSquares;
import org.ddogleg.optimization.impl.CommonChecksUnconstrainedLeastSquares_DDRM;
import org.ddogleg.optimization.impl.CommonChecksUnconstrainedLeastSquares_DSCC;
import org.ddogleg.optimization.math.HessianLeastSquares_DDRM;
import org.ddogleg.optimization.math.HessianLeastSquares_DSCC;
import org.ddogleg.optimization.math.MatrixMath_DDRM;
import org.ddogleg.optimization.math.MatrixMath_DSCC;
import org.ejml.data.DMatrixRMaj;
import org.ejml.data.DMatrixSparseCSC;
import org.ejml.dense.row.factory.LinearSolverFactory_DDRM;
import org.ejml.interfaces.linsol.LinearSolverDense;
import org.ejml.interfaces.linsol.LinearSolverSparse;
import org.ejml.sparse.FillReducing;
import org.ejml.sparse.csc.factory.LinearSolverFactory_DSCC;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Peter Abeles
 */
public class TestUnconLeastSqLevenbergMarquardt_F64 {
	@Test
	public void computeGradientHessian() {
		fail("Implement");
	}

	@Test
	public void computeResiduals() {
		fail("Implement");
	}

	@Nested
	class LeastSquaresDDRM extends CommonChecksUnconstrainedLeastSquares_DDRM {

		@Override
		protected UnconstrainedLeastSquares<DMatrixRMaj> createSearch(double minimumValue) {
			ConfigLevenbergMarquardt config = new ConfigLevenbergMarquardt();

//			config.mixture = 0.0;

			LinearSolverDense<DMatrixRMaj> solver = LinearSolverFactory_DDRM.chol(2);
			HessianLeastSquares_DDRM hessian = new HessianLeastSquares_DDRM(solver);
			UnconLeastSqLevenbergMarquardt_F64<DMatrixRMaj> lm = new UnconLeastSqLevenbergMarquardt_F64<>(new MatrixMath_DDRM(),hessian);
			lm.configure(config);
//			lm.setVerbose(true);
			return lm;
		}
	}

	@Nested
	class LeastSquaresDSCC extends CommonChecksUnconstrainedLeastSquares_DSCC {

		@Override
		protected UnconstrainedLeastSquares<DMatrixSparseCSC> createSearch(double minimumValue) {
			ConfigLevenbergMarquardt config = new ConfigLevenbergMarquardt();

//			config.mixture = 0.0;

			LinearSolverSparse<DMatrixSparseCSC,DMatrixRMaj> solver = LinearSolverFactory_DSCC.cholesky(FillReducing.NONE);
			HessianLeastSquares_DSCC hessian = new HessianLeastSquares_DSCC(solver);
			UnconLeastSqLevenbergMarquardt_F64<DMatrixSparseCSC> lm = new UnconLeastSqLevenbergMarquardt_F64<>(new MatrixMath_DSCC(),hessian);
			lm.configure(config);
//			lm.setVerbose(true);
			return lm;
		}
	}
}